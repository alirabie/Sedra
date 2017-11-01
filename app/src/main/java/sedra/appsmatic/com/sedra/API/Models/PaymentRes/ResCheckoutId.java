package sedra.appsmatic.com.sedra.API.Models.PaymentRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 6/5/2017.
 */
public class ResCheckoutId {
    @SerializedName("responseData")
    @Expose
    private ResponseData responseData;

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
}
