package sedra.appsmatic.com.sedra.API.Models.Categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 4/18/2017.
 */
public class Category {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("category_template_id")
    @Expose
    private Integer categoryTemplateId;
    @SerializedName("meta_keywords")
    @Expose
    private Object metaKeywords;
    @SerializedName("meta_description")
    @Expose
    private Object metaDescription;
    @SerializedName("meta_title")
    @Expose
    private Object metaTitle;
    @SerializedName("parent_category_id")
    @Expose
    private Integer parentCategoryId;
    @SerializedName("page_size")
    @Expose
    private Integer pageSize;
    @SerializedName("page_size_options")
    @Expose
    private String pageSizeOptions;
    @SerializedName("price_ranges")
    @Expose
    private Object priceRanges;
    @SerializedName("show_on_home_page")
    @Expose
    private Boolean showOnHomePage;
    @SerializedName("include_in_top_menu")
    @Expose
    private Boolean includeInTopMenu;
    @SerializedName("has_discounts_applied")
    @Expose
    private Object hasDiscountsApplied;
    @SerializedName("published")
    @Expose
    private Boolean published;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("created_on_utc")
    @Expose
    private String createdOnUtc;
    @SerializedName("updated_on_utc")
    @Expose
    private String updatedOnUtc;
    @SerializedName("role_ids")
    @Expose
    private List<Object> roleIds = null;
    @SerializedName("discount_ids")
    @Expose
    private List<Object> discountIds = null;
    @SerializedName("store_ids")
    @Expose
    private List<Object> storeIds = null;
    @SerializedName("image")
    @Expose
    private CategoryImage image;
    @SerializedName("se_name")
    @Expose
    private String seName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Integer getCategoryTemplateId() {
        return categoryTemplateId;
    }

    public void setCategoryTemplateId(Integer categoryTemplateId) {
        this.categoryTemplateId = categoryTemplateId;
    }

    public Object getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(Object metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public Object getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(Object metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Object getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(Object metaTitle) {
        this.metaTitle = metaTitle;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageSizeOptions() {
        return pageSizeOptions;
    }

    public void setPageSizeOptions(String pageSizeOptions) {
        this.pageSizeOptions = pageSizeOptions;
    }

    public Object getPriceRanges() {
        return priceRanges;
    }

    public void setPriceRanges(Object priceRanges) {
        this.priceRanges = priceRanges;
    }

    public Boolean getShowOnHomePage() {
        return showOnHomePage;
    }

    public void setShowOnHomePage(Boolean showOnHomePage) {
        this.showOnHomePage = showOnHomePage;
    }

    public Boolean getIncludeInTopMenu() {
        return includeInTopMenu;
    }

    public void setIncludeInTopMenu(Boolean includeInTopMenu) {
        this.includeInTopMenu = includeInTopMenu;
    }

    public Object getHasDiscountsApplied() {
        return hasDiscountsApplied;
    }

    public void setHasDiscountsApplied(Object hasDiscountsApplied) {
        this.hasDiscountsApplied = hasDiscountsApplied;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
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

    public List<Object> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Object> roleIds) {
        this.roleIds = roleIds;
    }

    public List<Object> getDiscountIds() {
        return discountIds;
    }

    public void setDiscountIds(List<Object> discountIds) {
        this.discountIds = discountIds;
    }

    public List<Object> getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(List<Object> storeIds) {
        this.storeIds = storeIds;
    }

    public CategoryImage getImage() {
        return image;
    }

    public void setImage(CategoryImage image) {
        this.image = image;
    }

    public String getSeName() {
        return seName;
    }

    public void setSeName(String seName) {
        this.seName = seName;
    }
}
