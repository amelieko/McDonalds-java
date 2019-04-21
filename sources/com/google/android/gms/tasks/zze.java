package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

class zze<TResult> implements zzf<TResult> {
    private final Executor zzboH;
    private OnSuccessListener<? super TResult> zzbwt;
    private final Object zzpp = new Object();

    public zze(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzboH = executor;
        this.zzbwt = onSuccessListener;
    }

    public void cancel() {
        synchronized (this.zzpp) {
            this.zzbwt = null;
        }
    }

    public void onComplete(@NonNull final Task<TResult> task) {
        if (task.isSuccessful()) {
            synchronized (this.zzpp) {
                if (this.zzbwt == null) {
                    return;
                }
                this.zzboH.execute(new Runnable() {
                    public void run() {
                        synchronized (zze.this.zzpp) {
                            if (zze.this.zzbwt != null) {
                                zze.this.zzbwt.onSuccess(task.getResult());
                            }
                        }
                    }
                });
            }
        }
    }
}
