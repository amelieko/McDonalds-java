package com.baidu.android.pushservice.p037i;

import com.mcdonalds.sdk.modules.models.Promotion;
import com.newrelic.agent.android.analytics.AnalyticAttribute;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.android.pushservice.i.k */
public class C1446k extends C1435r {
    /* renamed from: a */
    public int f5105a;

    public C1446k(C1435r c1435r) {
        super(c1435r);
    }

    /* renamed from: a */
    public JSONObject mo13904a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Promotion.COLUMN_ACTION_NAME, this.f5036f);
        jSONObject.put(AnalyticAttribute.EVENT_TIMESTAMP_ATTRIBUTE, this.f5037g);
        jSONObject.put("network_status", this.f5038h);
        jSONObject.put("heart", this.f5105a);
        jSONObject.put("err_code", this.f5039i);
        jSONObject.put("msg_result", this.f5042l);
        return jSONObject;
    }
}
