package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.p000v4.content.WakefulBroadcastReceiver;
import android.util.Base64;
import android.util.Log;
import com.autonavi.amap.mapcore.VTMCDataCache;
import com.mcdonalds.sdk.modules.notification.PushConstants;

public final class FirebaseInstanceIdReceiver extends WakefulBroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (isOrderedBroadcast()) {
            setResultCode(VTMCDataCache.MAXSIZE);
        }
        intent.setComponent(null);
        intent.setPackage(context.getPackageName());
        if (VERSION.SDK_INT <= 18) {
            intent.removeCategory(context.getPackageName());
        }
        String stringExtra = intent.getStringExtra("gcm.rawData64");
        if (stringExtra != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra, 0));
            intent.removeExtra("gcm.rawData64");
        }
        stringExtra = intent.getStringExtra(PushConstants.FROM_ID);
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction()) || "google.com/iid".equals(stringExtra) || "gcm.googleapis.com/refresh".equals(stringExtra)) {
            stringExtra = "com.google.firebase.INSTANCE_ID_EVENT";
        } else if ("com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            stringExtra = "com.google.firebase.MESSAGING_EVENT";
        } else {
            Log.d("FirebaseInstanceId", "Unexpected intent");
            stringExtra = null;
        }
        int i = -1;
        if (stringExtra != null) {
            i = FirebaseInstanceIdInternalReceiver.zzb(context, stringExtra, intent);
        }
        if (isOrderedBroadcast()) {
            setResultCode(i);
        }
    }
}
