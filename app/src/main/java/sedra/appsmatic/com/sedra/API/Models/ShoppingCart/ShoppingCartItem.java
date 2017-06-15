package sedra.appsmatic.com.sedra.API.Models.ShoppingCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 6/14/2017.
 */
public class ShoppingCartItem {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("customer_entered_price")
    @Expose
    private Object customerEnteredPrice;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("rental_start_date_utc")
    @Expose
    private Object rentalStartDateUtc;
    @SerializedName("rental_end_date_utc")
    @Expose
    private Object rentalEndDateUtc;
    @SerializedName("created_on_utc")
    @Expose
    private String createdOnUtc;
    @SerializedName("updated_on_utc")
    @Expose
    private String updatedOnUtc;
    @SerializedName("shopping_cart_type")
    @Expose
    private String shoppingCartType;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("customer_id")
    @Expose
    private String customerId;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getCustomerEnteredPrice() {
        return customerEnteredPrice;
    }

    public void setCustomerEnteredPrice(Object customerEnteredPrice) {
        this.customerEnteredPrice = customerEnteredPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public String getUpdatedOnUtc() {
        return updatedOnUtc;
    }

    public void setUpdatedOnUtc(String updatedOnUtc) {
        this.updatedOnUtc = updatedOnUtc;
    }

    public String getShoppingCartType() {
        return shoppingCartType;
    }

    public void setShoppingCartType(String shoppingCartType) {
        this.shoppingCartType = shoppingCartType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public Object getRentalStartDateUtc() {
        return rentalStartDateUtc;
    }

    public void setRentalStartDateUtc(Object rentalStartDateUtc) {
        this.rentalStartDateUtc = rentalStartDateUtc;
    }

    public Object getRentalEndDateUtc() {
        return rentalEndDateUtc;
    }

    public void setRentalEndDateUtc(Object rentalEndDateUtc) {
        this.rentalEndDateUtc = rentalEndDateUtc;
    }
}
