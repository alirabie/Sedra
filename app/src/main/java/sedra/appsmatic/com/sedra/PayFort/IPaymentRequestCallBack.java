package sedra.appsmatic.com.sedra.PayFort;

/**
 * Created by Eng Ali on 5/28/2017.
 */
public interface IPaymentRequestCallBack {
    void onPaymentRequestResponse(int responseType, PayFortData responseData);
}
