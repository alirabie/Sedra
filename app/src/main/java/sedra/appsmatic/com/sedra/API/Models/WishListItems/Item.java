package sedra.appsmatic.com.sedra.API.Models.WishListItems;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 7/4/2017.
 */
public class Item {

    @SerializedName("Sku")
    @Expose
    private String sku;
    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ProductSeName")
    @Expose
    private Object productSeName;
    @SerializedName("UnitPrice")
    @Expose
    private String unitPrice;
    @SerializedName("SubTotal")
    @Expose
    private String subTotal;
    @SerializedName("Discount")
    @Expose
    private Object discount;
    @SerializedName("MaximumDiscountedQty")
    @Expose
    private Object maximumDiscountedQty;
    @SerializedName("Quantity")
    @Expose
    private Integer quantity;
    @SerializedName("AllowedQuantities")
    @Expose
    private List<Object> allowedQuantities = null;
    @SerializedName("AttributeInfo")
    @Expose
    private String attributeInfo;
    @SerializedName("RecurringInfo")
    @Expose
    private Object recurringInfo;
    @SerializedName("RentalInfo")
    @Expose
    private Object rentalInfo;
    @SerializedName("AllowItemEditing")
    @Expose
    private Boolean allowItemEditing;
    @SerializedName("Warnings")
    @Expose
    private List<Object> warnings = null;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("CustomProperties")
    @Expose
    private CustomProperties customProperties;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Object getProductSeName() {
        return productSeName;
    }

    public void setProductSeName(Object productSeName) {
        this.productSeName = productSeName;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

    public Object getMaximumDiscountedQty() {
        return maximumDiscountedQty;
    }

    public void setMaximumDiscountedQty(Object maximumDiscountedQty) {
        this.maximumDiscountedQty = maximumDiscountedQty;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Object> getAllowedQuantities() {
        return allowedQuantities;
    }

    public void setAllowedQuantities(List<Object> allowedQuantities) {
        this.allowedQuantities = allowedQuantities;
    }

    public String getAttributeInfo() {
        return attributeInfo;
    }

    public void setAttributeInfo(String attributeInfo) {
        this.attributeInfo = attributeInfo;
    }

    public Object getRecurringInfo() {
        return recurringInfo;
    }

    public void setRecurringInfo(Object recurringInfo) {
        this.recurringInfo = recurringInfo;
    }

    public Object getRentalInfo() {
        return rentalInfo;
    }

    public void setRentalInfo(Object rentalInfo) {
        this.rentalInfo = rentalInfo;
    }

    public Boolean getAllowItemEditing() {
        return allowItemEditing;
    }

    public void setAllowItemEditing(Boolean allowItemEditing) {
        this.allowItemEditing = allowItemEditing;
    }

    public List<Object> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomProperties getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }
}
