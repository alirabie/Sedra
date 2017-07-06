package sedra.appsmatic.com.sedra.API.Models.WishListItems;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 7/4/2017.
 */
public class ResAddingWishList {
    @SerializedName("CustomerGuid")
    @Expose
    private String customerGuid;
    @SerializedName("CustomerFullname")
    @Expose
    private Object customerFullname;
    @SerializedName("EmailWishlistEnabled")
    @Expose
    private Boolean emailWishlistEnabled;
    @SerializedName("ShowSku")
    @Expose
    private Boolean showSku;
    @SerializedName("ShowProductImages")
    @Expose
    private Boolean showProductImages;
    @SerializedName("IsEditable")
    @Expose
    private Boolean isEditable;
    @SerializedName("DisplayAddToCart")
    @Expose
    private Boolean displayAddToCart;
    @SerializedName("DisplayTaxShippingInfo")
    @Expose
    private Boolean displayTaxShippingInfo;
    @SerializedName("Items")
    @Expose
    private List<Item> items = null;
    @SerializedName("Warnings")
    @Expose
    private List<Object> warnings = null;
    @SerializedName("CustomProperties")
    @Expose
    private CustomProperties_ customProperties;

    public String getCustomerGuid() {
        return customerGuid;
    }

    public void setCustomerGuid(String customerGuid) {
        this.customerGuid = customerGuid;
    }

    public Object getCustomerFullname() {
        return customerFullname;
    }

    public void setCustomerFullname(Object customerFullname) {
        this.customerFullname = customerFullname;
    }

    public Boolean getEmailWishlistEnabled() {
        return emailWishlistEnabled;
    }

    public void setEmailWishlistEnabled(Boolean emailWishlistEnabled) {
        this.emailWishlistEnabled = emailWishlistEnabled;
    }

    public Boolean getShowSku() {
        return showSku;
    }

    public void setShowSku(Boolean showSku) {
        this.showSku = showSku;
    }

    public Boolean getShowProductImages() {
        return showProductImages;
    }

    public void setShowProductImages(Boolean showProductImages) {
        this.showProductImages = showProductImages;
    }

    public Boolean getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }

    public Boolean getDisplayAddToCart() {
        return displayAddToCart;
    }

    public void setDisplayAddToCart(Boolean displayAddToCart) {
        this.displayAddToCart = displayAddToCart;
    }

    public Boolean getDisplayTaxShippingInfo() {
        return displayTaxShippingInfo;
    }

    public void setDisplayTaxShippingInfo(Boolean displayTaxShippingInfo) {
        this.displayTaxShippingInfo = displayTaxShippingInfo;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Object> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
    }

    public CustomProperties_ getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties(CustomProperties_ customProperties) {
        this.customProperties = customProperties;
    }}
