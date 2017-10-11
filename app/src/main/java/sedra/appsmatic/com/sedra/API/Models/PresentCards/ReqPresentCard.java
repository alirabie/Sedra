package sedra.appsmatic.com.sedra.API.Models.PresentCards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 10/11/2017.
 */
public class ReqPresentCard {

    @SerializedName("order_card")
    @Expose
    private OrderCard orderCard;

    public OrderCard getOrderCard() {
        return orderCard;
    }

    public void setOrderCard(OrderCard orderCard) {
        this.orderCard = orderCard;
    }
}
