package sedra.appsmatic.com.sedra.API.Models.Vendors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 5/3/2017.
 */
public class ResVendors {
    @SerializedName("vendors")
    @Expose
    private List<Vendor> vendors = null;

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }
}
