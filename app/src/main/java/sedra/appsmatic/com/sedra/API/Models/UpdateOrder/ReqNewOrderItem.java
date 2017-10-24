package sedra.appsmatic.com.sedra.API.Models.UpdateOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 10/24/2017.
 */
public class ReqNewOrderItem {
    @SerializedName("order_item")
    @Expose
    private OrderItem orderItem;

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
}
