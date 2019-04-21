package p041io.fabric.sdk.android.services.network;

import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import p041io.fabric.sdk.android.Fabric;

/* renamed from: io.fabric.sdk.android.services.network.PinningTrustManager */
class PinningTrustManager implements X509TrustManager {
    private static final X509Certificate[] NO_ISSUERS = new X509Certificate[0];
    private final Set<X509Certificate> cache = Collections.synchronizedSet(new HashSet());
    private final long pinCreationTimeMillis;
    private final List<byte[]> pins = new LinkedList();
    private final SystemKeyStore systemKeyStore;
    private final TrustManager[] systemTrustManagers;

    public PinningTrustManager(SystemKeyStore keyStore, PinningInfoProvider pinningInfoProvider) {
        this.systemTrustManagers = initializeSystemTrustManagers(keyStore);
        this.systemKeyStore = keyStore;
        this.pinCreationTimeMillis = pinningInfoProvider.getPinCreationTimeInMillis();
        for (String pin : pinningInfoProvider.getPins()) {
            this.pins.add(hexStringToByteArray(pin));
        }
    }

    private TrustManager[] initializeSystemTrustManagers(SystemKeyStore keyStore) {
        try {
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
            tmf.init(keyStore.trustStore);
            return tmf.getTrustManagers();
        } catch (NoSuchAlgorithmException nsae) {
            throw new AssertionError(nsae);
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        }
    }

    private boolean isValidPin(X509Certificate certificate) throws CertificateException {
        try {
            byte[] pin = MessageDigest.getInstance("SHA1").digest(certificate.getPublicKey().getEncoded());
            for (byte[] validPin : this.pins) {
                if (Arrays.equals(validPin, pin)) {
                    return true;
                }
            }
            return false;
        } catch (NoSuchAlgorithmException nsae) {
            throw new CertificateException(nsae);
        }
    }

    private void checkSystemTrust(X509Certificate[] chain, String authType) throws CertificateException {
        for (TrustManager systemTrustManager : this.systemTrustManagers) {
            ((X509TrustManager) systemTrustManager).checkServerTrusted(chain, authType);
        }
    }

    private void checkPinTrust(X509Certificate[] chain) throws CertificateException {
        if (this.pinCreationTimeMillis == -1 || System.currentTimeMillis() - this.pinCreationTimeMillis <= 15552000000L) {
            X509Certificate[] cleanChain = CertificateChainCleaner.getCleanChain(chain, this.systemKeyStore);
            int length = cleanChain.length;
            int i = 0;
            while (i < length) {
                if (!isValidPin(cleanChain[i])) {
                    i++;
                } else {
                    return;
                }
            }
            throw new CertificateException("No valid pins found in chain!");
        }
        Fabric.getLogger().mo34411w("Fabric", "Certificate pins are stale, (" + (System.currentTimeMillis() - this.pinCreationTimeMillis) + " millis vs " + 15552000000L + " millis) falling back to system trust.");
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (!this.cache.contains(chain[0])) {
            checkSystemTrust(chain, authType);
            checkPinTrust(chain);
            this.cache.add(chain[0]);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return NO_ISSUERS;
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[(len / 2)];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
