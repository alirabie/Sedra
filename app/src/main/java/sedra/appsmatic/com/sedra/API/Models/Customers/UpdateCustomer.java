package sedra.appsmatic.com.sedra.API.Models.Customers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import sedra.appsmatic.com.sedra.API.Models.Registration.RCustomer;

/**
 * Created by Eng Ali on 9/13/2017.
 */
public class UpdateCustomer {

    @SerializedName("customer")
    @Expose
    private RCustomer customer;

    public RCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(RCustomer customer) {
        this.customer = customer;
    }
}
