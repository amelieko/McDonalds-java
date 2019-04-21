package p041io.fabric.sdk.android.services.settings;

import android.content.Context;
import android.graphics.BitmapFactory.Options;
import com.newrelic.agent.android.instrumentation.BitmapFactoryInstrumentation;
import p041io.fabric.sdk.android.Fabric;
import p041io.fabric.sdk.android.services.common.CommonUtils;

/* renamed from: io.fabric.sdk.android.services.settings.IconRequest */
public class IconRequest {
    public final String hash;
    public final int height;
    public final int iconResourceId;
    public final int width;

    public IconRequest(String hash, int iconResourceId, int width, int height) {
        this.hash = hash;
        this.iconResourceId = iconResourceId;
        this.width = width;
        this.height = height;
    }

    public static IconRequest build(Context context, String iconHash) {
        if (iconHash == null) {
            return null;
        }
        try {
            int iconId = CommonUtils.getAppIconResourceId(context);
            Fabric.getLogger().mo34403d("Fabric", "App icon resource ID is " + iconId);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactoryInstrumentation.decodeResource(context.getResources(), iconId, options);
            return new IconRequest(iconHash, iconId, options.outWidth, options.outHeight);
        } catch (Exception e) {
            Fabric.getLogger().mo34406e("Fabric", "Failed to load icon", e);
            return null;
        }
    }
}
