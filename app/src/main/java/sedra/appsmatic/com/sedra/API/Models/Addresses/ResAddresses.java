package sedra.appsmatic.com.sedra.API.Models.Addresses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 5/3/2017.
 */
public class ResAddresses {
    @SerializedName("Addresses")
    @Expose
    private List<Address> addresses = null;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
