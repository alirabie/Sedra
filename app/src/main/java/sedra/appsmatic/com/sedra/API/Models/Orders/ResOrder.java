package sedra.appsmatic.com.sedra.API.Models.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import sedra.appsmatic.com.sedra.API.Models.Customers.BillingAddress;

/**
 * Created by Eng Ali on 9/6/2017.
 */
public class ResOrder {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("store_id")
    @Expose
    private Integer storeId;
    @SerializedName("pick_up_in_store")
    @Expose
    private Boolean pickUpInStore;
    @SerializedName("payment_method_system_name")
    @Expose
    private String paymentMethodSystemName;
    @SerializedName("customer_currency_code")
    @Expose
    private String customerCurrencyCode;
    @SerializedName("currency_rate")
    @Expose
    private Integer currencyRate;
    @SerializedName("customer_tax_display_type_id")
    @Expose
    private Integer customerTaxDisplayTypeId;
    @SerializedName("vat_number")
    @Expose
    private Object vatNumber;
    @SerializedName("order_subtotal_incl_tax")
    @Expose
    private Integer orderSubtotalInclTax;
    @SerializedName("order_subtotal_excl_tax")
    @Expose
    private Integer orderSubtotalExclTax;
    @SerializedName("order_sub_total_discount_incl_tax")
    @Expose
    private Integer orderSubTotalDiscountInclTax;
    @SerializedName("order_sub_total_discount_excl_tax")
    @Expose
    private Integer orderSubTotalDiscountExclTax;
    @SerializedName("order_shipping_incl_tax")
    @Expose
    private Integer orderShippingInclTax;
    @SerializedName("order_shipping_excl_tax")
    @Expose
    private Integer orderShippingExclTax;
    @SerializedName("payment_method_additional_fee_incl_tax")
    @Expose
    private Integer paymentMethodAdditionalFeeInclTax;
    @SerializedName("payment_method_additional_fee_excl_tax")
    @Expose
    private Integer paymentMethodAdditionalFeeExclTax;
    @SerializedName("tax_rates")
    @Expose
    private String taxRates;
    @SerializedName("order_tax")
    @Expose
    private Integer orderTax;
    @SerializedName("order_discount")
    @Expose
    private Integer orderDiscount;
    @SerializedName("order_total")
    @Expose
    private Integer orderTotal;
    @SerializedName("refunded_amount")
    @Expose
    private Integer refundedAmount;
    @SerializedName("reward_points_were_added")
    @Expose
    private Object rewardPointsWereAdded;
    @SerializedName("checkout_attribute_description")
    @Expose
    private String checkoutAttributeDescription;
    @SerializedName("customer_language_id")
    @Expose
    private Integer customerLanguageId;
    @SerializedName("affiliate_id")
    @Expose
    private Integer affiliateId;
    @SerializedName("customer_ip")
    @Expose
    private String customerIp;
    @SerializedName("authorization_transaction_id")
    @Expose
    private Object authorizationTransactionId;
    @SerializedName("authorization_transaction_code")
    @Expose
    private Object authorizationTransactionCode;
    @SerializedName("authorization_transaction_result")
    @Expose
    private Object authorizationTransactionResult;
    @SerializedName("capture_transaction_id")
    @Expose
    private Object captureTransactionId;
    @SerializedName("capture_transaction_result")
    @Expose
    private Object captureTransactionResult;
    @SerializedName("subscription_transaction_id")
    @Expose
    private Object subscriptionTransactionId;
    @SerializedName("paid_date_utc")
    @Expose
    private Object paidDateUtc;
    @SerializedName("shipping_method")
    @Expose
    private Object shippingMethod;
    @SerializedName("shipping_rate_computation_method_system_name")
    @Expose
    private Object shippingRateComputationMethodSystemName;
    @SerializedName("custom_values_xml")
    @Expose
    private Object customValuesXml;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("created_on_utc")
    @Expose
    private String createdOnUtc;
    @SerializedName("customer")
    @Expose
    private ResCustomer customer;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("billing_address")
    @Expose
    private BillingAddress billingAddress;
    @SerializedName("shipping_address")
    @Expose
    private Object shippingAddress;
    @SerializedName("order_items")
    @Expose
    private List<OrderItem> orderItems = null;
    @SerializedName("order_status")
    @Expose
    private String orderStatus;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("shipping_status")
    @Expose
    private String shippingStatus;
    @SerializedName("customer_tax_display_type")
    @Expose
    private String customerTaxDisplayType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Boolean getPickUpInStore() {
        return pickUpInStore;
    }

    public void setPickUpInStore(Boolean pickUpInStore) {
        this.pickUpInStore = pickUpInStore;
    }

    public String getPaymentMethodSystemName() {
        return paymentMethodSystemName;
    }

    public void setPaymentMethodSystemName(String paymentMethodSystemName) {
        this.paymentMethodSystemName = paymentMethodSystemName;
    }

    public String getCustomerCurrencyCode() {
        return customerCurrencyCode;
    }

    public void setCustomerCurrencyCode(String customerCurrencyCode) {
        this.customerCurrencyCode = customerCurrencyCode;
    }

    public Integer getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(Integer currencyRate) {
        this.currencyRate = currencyRate;
    }

    public Integer getCustomerTaxDisplayTypeId() {
        return customerTaxDisplayTypeId;
    }

    public void setCustomerTaxDisplayTypeId(Integer customerTaxDisplayTypeId) {
        this.customerTaxDisplayTypeId = customerTaxDisplayTypeId;
    }

    public Object getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(Object vatNumber) {
        this.vatNumber = vatNumber;
    }

    public Integer getOrderSubtotalInclTax() {
        return orderSubtotalInclTax;
    }

    public void setOrderSubtotalInclTax(Integer orderSubtotalInclTax) {
        this.orderSubtotalInclTax = orderSubtotalInclTax;
    }

    public Integer getOrderSubtotalExclTax() {
        return orderSubtotalExclTax;
    }

    public void setOrderSubtotalExclTax(Integer orderSubtotalExclTax) {
        this.orderSubtotalExclTax = orderSubtotalExclTax;
    }

    public Integer getOrderSubTotalDiscountInclTax() {
        return orderSubTotalDiscountInclTax;
    }

    public void setOrderSubTotalDiscountInclTax(Integer orderSubTotalDiscountInclTax) {
        this.orderSubTotalDiscountInclTax = orderSubTotalDiscountInclTax;
    }

    public Integer getOrderSubTotalDiscountExclTax() {
        return orderSubTotalDiscountExclTax;
    }

    public void setOrderSubTotalDiscountExclTax(Integer orderSubTotalDiscountExclTax) {
        this.orderSubTotalDiscountExclTax = orderSubTotalDiscountExclTax;
    }

    public Integer getOrderShippingInclTax() {
        return orderShippingInclTax;
    }

    public void setOrderShippingInclTax(Integer orderShippingInclTax) {
        this.orderShippingInclTax = orderShippingInclTax;
    }

    public Integer getOrderShippingExclTax() {
        return orderShippingExclTax;
    }

    public void setOrderShippingExclTax(Integer orderShippingExclTax) {
        this.orderShippingExclTax = orderShippingExclTax;
    }

    public Integer getPaymentMethodAdditionalFeeInclTax() {
        return paymentMethodAdditionalFeeInclTax;
    }

    public void setPaymentMethodAdditionalFeeInclTax(Integer paymentMethodAdditionalFeeInclTax) {
        this.paymentMethodAdditionalFeeInclTax = paymentMethodAdditionalFeeInclTax;
    }

    public Integer getPaymentMethodAdditionalFeeExclTax() {
        return paymentMethodAdditionalFeeExclTax;
    }

    public void setPaymentMethodAdditionalFeeExclTax(Integer paymentMethodAdditionalFeeExclTax) {
        this.paymentMethodAdditionalFeeExclTax = paymentMethodAdditionalFeeExclTax;
    }

    public String getTaxRates() {
        return taxRates;
    }

    public void setTaxRates(String taxRates) {
        this.taxRates = taxRates;
    }

    public Integer getOrderTax() {
        return orderTax;
    }

    public void setOrderTax(Integer orderTax) {
        this.orderTax = orderTax;
    }

    public Integer getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(Integer orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public Integer getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Integer orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Integer getRefundedAmount() {
        return refundedAmount;
    }

    public void setRefundedAmount(Integer refundedAmount) {
        this.refundedAmount = refundedAmount;
    }

    public Object getRewardPointsWereAdded() {
        return rewardPointsWereAdded;
    }

    public void setRewardPointsWereAdded(Object rewardPointsWereAdded) {
        this.rewardPointsWereAdded = rewardPointsWereAdded;
    }

    public String getCheckoutAttributeDescription() {
        return checkoutAttributeDescription;
    }

    public void setCheckoutAttributeDescription(String checkoutAttributeDescription) {
        this.checkoutAttributeDescription = checkoutAttributeDescription;
    }

    public Integer getCustomerLanguageId() {
        return customerLanguageId;
    }

    public void setCustomerLanguageId(Integer customerLanguageId) {
        this.customerLanguageId = customerLanguageId;
    }

    public Integer getAffiliateId() {
        return affiliateId;
    }

    public void setAffiliateId(Integer affiliateId) {
        this.affiliateId = affiliateId;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public Object getAuthorizationTransactionId() {
        return authorizationTransactionId;
    }

    public void setAuthorizationTransactionId(Object authorizationTransactionId) {
        this.authorizationTransactionId = authorizationTransactionId;
    }

    public Object getAuthorizationTransactionCode() {
        return authorizationTransactionCode;
    }

    public void setAuthorizationTransactionCode(Object authorizationTransactionCode) {
        this.authorizationTransactionCode = authorizationTransactionCode;
    }

    public Object getAuthorizationTransactionResult() {
        return authorizationTransactionResult;
    }

    public void setAuthorizationTransactionResult(Object authorizationTransactionResult) {
        this.authorizationTransactionResult = authorizationTransactionResult;
    }

    public Object getCaptureTransactionId() {
        return captureTransactionId;
    }

    public void setCaptureTransactionId(Object captureTransactionId) {
        this.captureTransactionId = captureTransactionId;
    }

    public Object getCaptureTransactionResult() {
        return captureTransactionResult;
    }

    public void setCaptureTransactionResult(Object captureTransactionResult) {
        this.captureTransactionResult = captureTransactionResult;
    }

    public Object getSubscriptionTransactionId() {
        return subscriptionTransactionId;
    }

    public void setSubscriptionTransactionId(Object subscriptionTransactionId) {
        this.subscriptionTransactionId = subscriptionTransactionId;
    }

    public Object getPaidDateUtc() {
        return paidDateUtc;
    }

    public void setPaidDateUtc(Object paidDateUtc) {
        this.paidDateUtc = paidDateUtc;
    }

    public Object getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(Object shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Object getShippingRateComputationMethodSystemName() {
        return shippingRateComputationMethodSystemName;
    }

    public void setShippingRateComputationMethodSystemName(Object shippingRateComputationMethodSystemName) {
        this.shippingRateComputationMethodSystemName = shippingRateComputationMethodSystemName;
    }

    public Object getCustomValuesXml() {
        return customValuesXml;
    }

    public void setCustomValuesXml(Object customValuesXml) {
        this.customValuesXml = customValuesXml;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public ResCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(ResCustomer customer) {
        this.customer = customer;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Object getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Object shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getCustomerTaxDisplayType() {
        return customerTaxDisplayType;
    }

    public void setCustomerTaxDisplayType(String customerTaxDisplayType) {
        this.customerTaxDisplayType = customerTaxDisplayType;
    }

}
