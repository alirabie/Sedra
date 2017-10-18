package sedra.appsmatic.com.sedra.API.Models.VendorDateSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 10/17/2017.
 */
public class ResVendorsSch {
    @SerializedName("deliveryschedules")
    @Expose
    private List<Deliveryschedule> deliveryschedules = null;

    public List<Deliveryschedule> getDeliveryschedules() {
        return deliveryschedules;
    }

    public void setDeliveryschedules(List<Deliveryschedule> deliveryschedules) {
        this.deliveryschedules = deliveryschedules;
    }
}
