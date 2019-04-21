package com.google.android.gms.common.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.util.Log;
import com.google.android.gms.internal.zzor;

public abstract class zzi implements OnClickListener {
    public static zzi zza(final Activity activity, final Intent intent, final int i) {
        return new zzi() {
            public void zztD() {
                activity.startActivityForResult(intent, i);
            }
        };
    }

    public static zzi zza(@NonNull final Fragment fragment, final Intent intent, final int i) {
        return new zzi() {
            public void zztD() {
                fragment.startActivityForResult(intent, i);
            }
        };
    }

    public static zzi zza(@NonNull final zzor zzor, final Intent intent, final int i) {
        return new zzi() {
            @TargetApi(11)
            public void zztD() {
                zzor.startActivityForResult(intent, i);
            }
        };
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            zztD();
            dialogInterface.dismiss();
        } catch (ActivityNotFoundException e) {
            Log.e("DialogRedirect", "Can't redirect to app settings for Google Play services");
        }
    }

    public abstract void zztD();
}
