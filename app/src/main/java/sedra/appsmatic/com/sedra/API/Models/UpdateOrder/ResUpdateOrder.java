package sedra.appsmatic.com.sedra.API.Models.UpdateOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 10/24/2017.
 */
public class ResUpdateOrder {

    @SerializedName("order_items")
    @Expose
    private List<UorderItem> orderItems = null;

    public List<UorderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<UorderItem> orderItems) {
        this.orderItems = orderItems;
    }




}
