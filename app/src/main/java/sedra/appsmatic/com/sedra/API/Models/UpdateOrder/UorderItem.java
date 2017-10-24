package sedra.appsmatic.com.sedra.API.Models.UpdateOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import sedra.appsmatic.com.sedra.API.Models.Productes.Product;

/**
 * Created by Eng Ali on 10/24/2017.
 */
public class UorderItem {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("item_guid")
    @Expose
    private String itemGuid;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("unit_price_incl_tax")
    @Expose
    private Integer unitPriceInclTax;
    @SerializedName("unit_price_excl_tax")
    @Expose
    private Integer unitPriceExclTax;
    @SerializedName("price_incl_tax")
    @Expose
    private Integer priceInclTax;
    @SerializedName("price_excl_tax")
    @Expose
    private Integer priceExclTax;
    @SerializedName("discount_amount_incl_tax")
    @Expose
    private Integer discountAmountInclTax;
    @SerializedName("discount_amount_excl_tax")
    @Expose
    private Integer discountAmountExclTax;
    @SerializedName("original_product_cost")
    @Expose
    private Integer originalProductCost;
    @SerializedName("attribute_description")
    @Expose
    private Object attributeDescription;
    @SerializedName("download_count")
    @Expose
    private Integer downloadCount;
    @SerializedName("isDownload_activated")
    @Expose
    private Boolean isDownloadActivated;
    @SerializedName("license_download_id")
    @Expose
    private Object licenseDownloadId;
    @SerializedName("item_weight")
    @Expose
    private Object itemWeight;
    @SerializedName("rental_start_date_utc")
    @Expose
    private Object rentalStartDateUtc;
    @SerializedName("rental_end_date_utc")
    @Expose
    private Object rentalEndDateUtc;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("product_id")
    @Expose
    private Integer productId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemGuid() {
        return itemGuid;
    }

    public void setItemGuid(String itemGuid) {
        this.itemGuid = itemGuid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPriceInclTax() {
        return unitPriceInclTax;
    }

    public void setUnitPriceInclTax(Integer unitPriceInclTax) {
        this.unitPriceInclTax = unitPriceInclTax;
    }

    public Integer getUnitPriceExclTax() {
        return unitPriceExclTax;
    }

    public void setUnitPriceExclTax(Integer unitPriceExclTax) {
        this.unitPriceExclTax = unitPriceExclTax;
    }

    public Integer getPriceInclTax() {
        return priceInclTax;
    }

    public void setPriceInclTax(Integer priceInclTax) {
        this.priceInclTax = priceInclTax;
    }

    public Integer getPriceExclTax() {
        return priceExclTax;
    }

    public void setPriceExclTax(Integer priceExclTax) {
        this.priceExclTax = priceExclTax;
    }

    public Integer getDiscountAmountInclTax() {
        return discountAmountInclTax;
    }

    public void setDiscountAmountInclTax(Integer discountAmountInclTax) {
        this.discountAmountInclTax = discountAmountInclTax;
    }

    public Integer getDiscountAmountExclTax() {
        return discountAmountExclTax;
    }

    public void setDiscountAmountExclTax(Integer discountAmountExclTax) {
        this.discountAmountExclTax = discountAmountExclTax;
    }

    public Integer getOriginalProductCost() {
        return originalProductCost;
    }

    public void setOriginalProductCost(Integer originalProductCost) {
        this.originalProductCost = originalProductCost;
    }

    public Object getAttributeDescription() {
        return attributeDescription;
    }

    public void setAttributeDescription(Object attributeDescription) {
        this.attributeDescription = attributeDescription;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Boolean getIsDownloadActivated() {
        return isDownloadActivated;
    }

    public void setIsDownloadActivated(Boolean isDownloadActivated) {
        this.isDownloadActivated = isDownloadActivated;
    }

    public Object getLicenseDownloadId() {
        return licenseDownloadId;
    }

    public void setLicenseDownloadId(Object licenseDownloadId) {
        this.licenseDownloadId = licenseDownloadId;
    }

    public Object getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(Object itemWeight) {
        this.itemWeight = itemWeight;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
