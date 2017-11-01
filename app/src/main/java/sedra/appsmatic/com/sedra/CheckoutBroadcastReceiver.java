package sedra.appsmatic.com.sedra;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.oppwa.mobile.connect.checkout.dialog.CheckoutActivity;
import com.oppwa.mobile.connect.payment.PaymentParamsBrand;

/**
 * Created by Eng Ali on 6/5/2017.
 */
public class CheckoutBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String paymentBrand = intent.getStringExtra(CheckoutActivity.EXTRA_PAYMENT_BRAND);
        String checkoutId = intent.getStringExtra(CheckoutActivity.EXTRA_CHECKOUT_ID);

        /* You can request a new checkout ID or just send back the same id to continue checkout process */
        /* In case of error send null checkout ID to close the CheckoutActivity */
        intent = new Intent(context, CheckoutActivity.class);
        intent.setAction(CheckoutActivity.ACTION_PAYMENT_METHOD_SELECTED);
        intent.putExtra(CheckoutActivity.EXTRA_CHECKOUT_ID, checkoutId);

        context.startActivity(intent);
    }


}
