package sedra.appsmatic.com.sedra.API.Models.Productes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 4/19/2017.
 */
public class Attribute {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_attribute_id")
    @Expose
    private Integer productAttributeId;
    @SerializedName("product_attribute_name")
    @Expose
    private String productAttributeName;
    @SerializedName("text_prompt")
    @Expose
    private Object textPrompt;
    @SerializedName("is_required")
    @Expose
    private Boolean isRequired;
    @SerializedName("attribute_control_type_id")
    @Expose
    private Integer attributeControlTypeId;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("default_value")
    @Expose
    private String defaultValue;
    @SerializedName("attribute_control_type_name")
    @Expose
    private String attributeControlTypeName;
    @SerializedName("attribute_values")
    @Expose
    private List<Object> attributeValues = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductAttributeId() {
        return productAttributeId;
    }

    public void setProductAttributeId(Integer productAttributeId) {
        this.productAttributeId = productAttributeId;
    }

    public String getProductAttributeName() {
        return productAttributeName;
    }

    public void setProductAttributeName(String productAttributeName) {
        this.productAttributeName = productAttributeName;
    }

    public Object getTextPrompt() {
        return textPrompt;
    }

    public void setTextPrompt(Object textPrompt) {
        this.textPrompt = textPrompt;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Integer getAttributeControlTypeId() {
        return attributeControlTypeId;
    }

    public void setAttributeControlTypeId(Integer attributeControlTypeId) {
        this.attributeControlTypeId = attributeControlTypeId;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getAttributeControlTypeName() {
        return attributeControlTypeName;
    }

    public void setAttributeControlTypeName(String attributeControlTypeName) {
        this.attributeControlTypeName = attributeControlTypeName;
    }

    public List<Object> getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(List<Object> attributeValues) {
        this.attributeValues = attributeValues;
    }

}
