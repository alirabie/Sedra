package sedra.appsmatic.com.sedra.API.Models.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 9/6/2017.
 */
public class ResCustomer {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("username")
    @Expose
    private Object username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("admin_comment")
    @Expose
    private Object adminComment;
    @SerializedName("is_tax_exempt")
    @Expose
    private Boolean isTaxExempt;
    @SerializedName("has_shopping_cart_items")
    @Expose
    private Boolean hasShoppingCartItems;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("is_system_account")
    @Expose
    private Boolean isSystemAccount;
    @SerializedName("system_name")
    @Expose
    private Object systemName;
    @SerializedName("last_ip_address")
    @Expose
    private Object lastIpAddress;
    @SerializedName("created_on_utc")
    @Expose
    private String createdOnUtc;
    @SerializedName("last_login_date_utc")
    @Expose
    private String lastLoginDateUtc;
    @SerializedName("last_activity_date_utc")
    @Expose
    private String lastActivityDateUtc;
    @SerializedName("role_ids")
    @Expose
    private List<Object> roleIds = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(Object adminComment) {
        this.adminComment = adminComment;
    }

    public Boolean getIsTaxExempt() {
        return isTaxExempt;
    }

    public void setIsTaxExempt(Boolean isTaxExempt) {
        this.isTaxExempt = isTaxExempt;
    }

    public Boolean getHasShoppingCartItems() {
        return hasShoppingCartItems;
    }

    public void setHasShoppingCartItems(Boolean hasShoppingCartItems) {
        this.hasShoppingCartItems = hasShoppingCartItems;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean getIsSystemAccount() {
        return isSystemAccount;
    }

    public void setIsSystemAccount(Boolean isSystemAccount) {
        this.isSystemAccount = isSystemAccount;
    }

    public Object getSystemName() {
        return systemName;
    }

    public void setSystemName(Object systemName) {
        this.systemName = systemName;
    }

    public Object getLastIpAddress() {
        return lastIpAddress;
    }

    public void setLastIpAddress(Object lastIpAddress) {
        this.lastIpAddress = lastIpAddress;
    }

    public String getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public String getLastLoginDateUtc() {
        return lastLoginDateUtc;
    }

    public void setLastLoginDateUtc(String lastLoginDateUtc) {
        this.lastLoginDateUtc = lastLoginDateUtc;
    }

    public String getLastActivityDateUtc() {
        return lastActivityDateUtc;
    }

    public void setLastActivityDateUtc(String lastActivityDateUtc) {
        this.lastActivityDateUtc = lastActivityDateUtc;
    }

    public List<Object> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Object> roleIds) {
        this.roleIds = roleIds;
    }
}
