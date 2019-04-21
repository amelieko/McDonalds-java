package p041io.fabric.sdk.android.services.concurrency.internal;

/* renamed from: io.fabric.sdk.android.services.concurrency.internal.ExponentialBackoff */
public class ExponentialBackoff implements Backoff {
    private final long baseTimeMillis;
    private final int power;

    public ExponentialBackoff(long baseTimeMillis, int power) {
        this.baseTimeMillis = baseTimeMillis;
        this.power = power;
    }

    public long getDelayMillis(int retries) {
        return (long) (((double) this.baseTimeMillis) * Math.pow((double) this.power, (double) retries));
    }
}
