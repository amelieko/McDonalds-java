package com.mcdonalds.sdk.connectors.middleware.request;

import android.text.TextUtils;
import com.mcdonalds.sdk.connectors.middleware.MiddlewareConnector;
import com.mcdonalds.sdk.connectors.middleware.response.MWJSONResponse;
import com.mcdonalds.sdk.services.network.CustomTypeAdapter;
import com.mcdonalds.sdk.services.network.RequestProvider.MethodType;
import com.mcdonalds.sdk.services.network.RequestProvider.RequestType;
import java.util.List;
import java.util.Map;

public class MWCustomerResetPasswordRequest extends MWRequest<MWJSONResponse, MWJSONRequestBody> {
    private static final String URL_PATH = "/customer/password";
    private final MWRequestHeaders mHeaderMap;
    private final MWJSONRequestBody mPostBody;

    @Deprecated
    public MWCustomerResetPasswordRequest(MiddlewareConnector ignored, String ecpToken, String userName) {
        this(ecpToken, userName);
    }

    public MWCustomerResetPasswordRequest(String ecpToken, String userName) {
        this.mHeaderMap = getHeaderMap(ecpToken);
        this.mPostBody = new MWJSONRequestBody();
        this.mPostBody.put("userName", userName);
    }

    @Deprecated
    public MWCustomerResetPasswordRequest(MiddlewareConnector ignored, String ecpToken, String userName, String emailAddress, String mobilePhone) {
        this(ecpToken, userName, emailAddress, mobilePhone);
    }

    public MWCustomerResetPasswordRequest(String ecpToken, String userName, String emailAddress, String mobilePhone) {
        this(ecpToken, userName);
        if (!TextUtils.isEmpty(mobilePhone)) {
            this.mPostBody.put("mobilePhone", mobilePhone);
        }
        if (!TextUtils.isEmpty(emailAddress)) {
            this.mPostBody.put("emailAddress", emailAddress);
        }
    }

    public MethodType getMethodType() {
        return MethodType.POST;
    }

    public RequestType getRequestType() {
        return RequestType.JSON;
    }

    /* Access modifiers changed, original: 0000 */
    public String getEndpoint() {
        return URL_PATH;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaderMap;
    }

    public String getBody() {
        return this.mPostBody.toJson();
    }

    public void setBody(MWJSONRequestBody body) {
    }

    public Class<MWJSONResponse> getResponseClass() {
        return MWJSONResponse.class;
    }

    public List<? extends CustomTypeAdapter> getCustomTypeAdapters() {
        return null;
    }
}
