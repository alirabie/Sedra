package sedra.appsmatic.com.sedra.API.Models.Productes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 4/19/2017.
 */
public class AttributeValue {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type_id")
    @Expose
    private Integer typeId;
    @SerializedName("associated_product_id")
    @Expose
    private Integer associatedProductId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("color_squares_rgb")
    @Expose
    private Object colorSquaresRgb;
    @SerializedName("image_squares_image")
    @Expose
    private Object imageSquaresImage;
    @SerializedName("price_adjustment")
    @Expose
    private Double priceAdjustment;
    @SerializedName("weight_adjustment")
    @Expose
    private Double weightAdjustment;
    @SerializedName("cost")
    @Expose
    private Double cost;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("is_pre_selected")
    @Expose
    private Boolean isPreSelected;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("product_image_id")
    @Expose
    private Object productImageId;
    @SerializedName("type")
    @Expose
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getAssociatedProductId() {
        return associatedProductId;
    }

    public void setAssociatedProductId(Integer associatedProductId) {
        this.associatedProductId = associatedProductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getColorSquaresRgb() {
        return colorSquaresRgb;
    }

    public void setColorSquaresRgb(Object colorSquaresRgb) {
        this.colorSquaresRgb = colorSquaresRgb;
    }

    public Object getImageSquaresImage() {
        return imageSquaresImage;
    }

    public void setImageSquaresImage(Object imageSquaresImage) {
        this.imageSquaresImage = imageSquaresImage;
    }

    public Double getPriceAdjustment() {
        return priceAdjustment;
    }

    public void setPriceAdjustment(Double priceAdjustment) {
        this.priceAdjustment = priceAdjustment;
    }

    public Double getWeightAdjustment() {
        return weightAdjustment;
    }

    public void setWeightAdjustment(Double weightAdjustment) {
        this.weightAdjustment = weightAdjustment;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getIsPreSelected() {
        return isPreSelected;
    }

    public void setIsPreSelected(Boolean isPreSelected) {
        this.isPreSelected = isPreSelected;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Object getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(Object productImageId) {
        this.productImageId = productImageId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
