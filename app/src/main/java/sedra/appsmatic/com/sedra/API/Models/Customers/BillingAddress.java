package sedra.appsmatic.com.sedra.API.Models.Customers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 7/3/2017.
 */
public class BillingAddress {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("first_name")
    @Expose
    private Object firstName;
    @SerializedName("last_name")
    @Expose
    private Object lastName;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("company")
    @Expose
    private Object company;
    @SerializedName("country_id")
    @Expose
    private Object countryId;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("state_province_id")
    @Expose
    private Object stateProvinceId;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("address1")
    @Expose
    private Object address1;
    @SerializedName("address2")
    @Expose
    private Object address2;
    @SerializedName("zip_postal_code")
    @Expose
    private Object zipPostalCode;
    @SerializedName("phone_number")
    @Expose
    private Object phoneNumber;
    @SerializedName("fax_number")
    @Expose
    private Object faxNumber;
    @SerializedName("customer_attributes")
    @Expose
    private Object customerAttributes;
    @SerializedName("created_on_utc")
    @Expose
    private String createdOnUtc;
    @SerializedName("province")
    @Expose
    private Object province;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getFirstName() {
        return firstName;
    }

    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public Object getCountryId() {
        return countryId;
    }

    public void setCountryId(Object countryId) {
        this.countryId = countryId;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Object getStateProvinceId() {
        return stateProvinceId;
    }

    public void setStateProvinceId(Object stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getAddress1() {
        return address1;
    }

    public void setAddress1(Object address1) {
        this.address1 = address1;
    }

    public Object getAddress2() {
        return address2;
    }

    public void setAddress2(Object address2) {
        this.address2 = address2;
    }

    public Object getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(Object zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public Object getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(Object faxNumber) {
        this.faxNumber = faxNumber;
    }

    public Object getCustomerAttributes() {
        return customerAttributes;
    }

    public void setCustomerAttributes(Object customerAttributes) {
        this.customerAttributes = customerAttributes;
    }

    public String getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public Object getProvince() {
        return province;
    }

    public void setProvince(Object province) {
        this.province = province;
    }



}
