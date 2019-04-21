package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzg implements Creator<WakeLockEvent> {
    static void zza(WakeLockEvent wakeLockEvent, Parcel parcel, int i) {
        int zzar = zzb.zzar(parcel);
        zzb.zzc(parcel, 1, wakeLockEvent.mVersionCode);
        zzb.zza(parcel, 2, wakeLockEvent.getTimeMillis());
        zzb.zza(parcel, 4, wakeLockEvent.zzuJ(), false);
        zzb.zzc(parcel, 5, wakeLockEvent.zzuM());
        zzb.zzb(parcel, 6, wakeLockEvent.zzuN(), false);
        zzb.zza(parcel, 8, wakeLockEvent.zzuF());
        zzb.zza(parcel, 10, wakeLockEvent.zzuK(), false);
        zzb.zzc(parcel, 11, wakeLockEvent.getEventType());
        zzb.zza(parcel, 12, wakeLockEvent.zzuC(), false);
        zzb.zza(parcel, 13, wakeLockEvent.zzuP(), false);
        zzb.zzc(parcel, 14, wakeLockEvent.zzuO());
        zzb.zza(parcel, 15, wakeLockEvent.zzuQ());
        zzb.zza(parcel, 16, wakeLockEvent.zzuR());
        zzb.zza(parcel, 17, wakeLockEvent.zzuL(), false);
        zzb.zzJ(parcel, zzar);
    }

    /* renamed from: zzaC */
    public WakeLockEvent createFromParcel(Parcel parcel) {
        int zzaq = zza.zzaq(parcel);
        int i = 0;
        long j = 0;
        int i2 = 0;
        String str = null;
        int i3 = 0;
        List list = null;
        String str2 = null;
        long j2 = 0;
        int i4 = 0;
        String str3 = null;
        String str4 = null;
        float f = 0.0f;
        long j3 = 0;
        String str5 = null;
        while (parcel.dataPosition() < zzaq) {
            int zzap = zza.zzap(parcel);
            switch (zza.zzcj(zzap)) {
                case 1:
                    i = zza.zzg(parcel, zzap);
                    break;
                case 2:
                    j = zza.zzi(parcel, zzap);
                    break;
                case 4:
                    str = zza.zzq(parcel, zzap);
                    break;
                case 5:
                    i3 = zza.zzg(parcel, zzap);
                    break;
                case 6:
                    list = zza.zzE(parcel, zzap);
                    break;
                case 8:
                    j2 = zza.zzi(parcel, zzap);
                    break;
                case 10:
                    str3 = zza.zzq(parcel, zzap);
                    break;
                case 11:
                    i2 = zza.zzg(parcel, zzap);
                    break;
                case 12:
                    str2 = zza.zzq(parcel, zzap);
                    break;
                case 13:
                    str4 = zza.zzq(parcel, zzap);
                    break;
                case 14:
                    i4 = zza.zzg(parcel, zzap);
                    break;
                case 15:
                    f = zza.zzl(parcel, zzap);
                    break;
                case 16:
                    j3 = zza.zzi(parcel, zzap);
                    break;
                case 17:
                    str5 = zza.zzq(parcel, zzap);
                    break;
                default:
                    zza.zzb(parcel, zzap);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaq) {
            return new WakeLockEvent(i, j, i2, str, i3, list, str2, j2, i4, str3, str4, f, j3, str5);
        }
        throw new zza.zza("Overread allowed size end=" + zzaq, parcel);
    }

    /* renamed from: zzcv */
    public WakeLockEvent[] newArray(int i) {
        return new WakeLockEvent[i];
    }
}
