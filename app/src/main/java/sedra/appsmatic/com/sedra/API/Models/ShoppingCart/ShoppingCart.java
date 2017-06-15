package sedra.appsmatic.com.sedra.API.Models.ShoppingCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import sedra.appsmatic.com.sedra.API.Models.Productes.Product;

/**
 * Created by Eng Ali on 6/14/2017.
 */
public class ShoppingCart {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("customer_entered_price")
    @Expose
    private Integer customerEnteredPrice;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
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
    private Integer productId;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("customer")
    @Expose
    private Customer customer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCustomerEnteredPrice() {
        return customerEnteredPrice;
    }

    public void setCustomerEnteredPrice(Integer customerEnteredPrice) {
        this.customerEnteredPrice = customerEnteredPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
