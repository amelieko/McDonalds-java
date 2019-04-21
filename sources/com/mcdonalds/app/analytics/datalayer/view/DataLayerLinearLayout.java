package com.mcdonalds.app.analytics.datalayer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.ensighten.Ensighten;

public class DataLayerLinearLayout extends LinearLayout {
    public DataLayerLinearLayout(Context context) {
        super(context);
    }

    public DataLayerLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DataLayerLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnClickListener(@Nullable OnClickListener l) {
        Ensighten.evaluateEvent(this, "setOnClickListener", new Object[]{l});
        super.setOnClickListener(new DataLayerClickListener(l));
    }
}
