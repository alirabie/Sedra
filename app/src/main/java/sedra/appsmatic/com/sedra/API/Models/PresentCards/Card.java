package sedra.appsmatic.com.sedra.API.Models.PresentCards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 10/11/2017.
 */
public class Card {
    @SerializedName("order_id")
    @Expose
    private Integer orderId;

    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("cardaddress")
    @Expose
    private Cardaddress cardaddress;
    @SerializedName("cardschedule")
    @Expose
    private Cardschedule cardschedule;
    @SerializedName("cardmessage")
    @Expose
    private Cardmessage cardmessage;

    public Cardaddress getCardaddress() {
        return cardaddress;
    }

    public void setCardaddress(Cardaddress cardaddress) {
        this.cardaddress = cardaddress;
    }

    public Cardschedule getCardschedule() {
        return cardschedule;
    }

    public void setCardschedule(Cardschedule cardschedule) {
        this.cardschedule = cardschedule;
    }

    public Cardmessage getCardmessage() {
        return cardmessage;
    }

    public void setCardmessage(Cardmessage cardmessage) {
        this.cardmessage = cardmessage;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


}
