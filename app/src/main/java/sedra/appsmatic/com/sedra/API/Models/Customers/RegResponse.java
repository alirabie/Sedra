package sedra.appsmatic.com.sedra.API.Models.Customers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import sedra.appsmatic.com.sedra.API.Models.Error.ResErrors;

/**
 * Created by Eng Ali on 7/3/2017.
 */
public class RegResponse extends ResErrors {

    @SerializedName("customers")
    @Expose
    private List<Customer> customers = null;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
