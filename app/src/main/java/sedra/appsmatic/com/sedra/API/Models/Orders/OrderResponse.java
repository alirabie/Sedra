package sedra.appsmatic.com.sedra.API.Models.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 9/6/2017.
 */
public class OrderResponse {
    @SerializedName("orders")
    @Expose
    private List<ResOrder> orders = null;

    public List<ResOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ResOrder> orders) {
        this.orders = orders;
    }
}