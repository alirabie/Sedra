package sedra.appsmatic.com.sedra.API.WebServiceTools;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sedra.appsmatic.com.sedra.API.Models.Addresses.ResAddresses;
import sedra.appsmatic.com.sedra.API.Models.Categories.ResCategories;
import sedra.appsmatic.com.sedra.API.Models.Countries.ResCountry;
import sedra.appsmatic.com.sedra.API.Models.Customers.RegResponse;
import sedra.appsmatic.com.sedra.API.Models.District.Districts;
import sedra.appsmatic.com.sedra.API.Models.Orders.OrderResponse;
import sedra.appsmatic.com.sedra.API.Models.Orders.ResDeleteOrderItem;
import sedra.appsmatic.com.sedra.API.Models.PaymentRes.ResCheckoutId;
import sedra.appsmatic.com.sedra.API.Models.PresentCards.ReqPresentCard;
import sedra.appsmatic.com.sedra.API.Models.Productes.Product;
import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
import sedra.appsmatic.com.sedra.API.Models.ShoppingCart.ResCartItems;
import sedra.appsmatic.com.sedra.API.Models.States.ResStates;
import sedra.appsmatic.com.sedra.API.Models.UpdateOrder.ResUpdateOrder;
import sedra.appsmatic.com.sedra.API.Models.VendorDateSchedule.ResVendorsSch;
import sedra.appsmatic.com.sedra.API.Models.Vendors.ResVendors;
import sedra.appsmatic.com.sedra.API.Models.WishListItems.ResAddingWishList;
import sedra.appsmatic.com.sedra.API.Models.verifications.VerificationCode;

/**
 * Created by Eng Ali on 4/18/2017.
 */
public interface SedraApi {

    //Get All Categories
    @GET("api/categories?fields=id,name,description,image")
    Call<ResCategories> getCategories();

    //Get Category Products by Id
    @GET("api/products?fields=id,images,price,name")
    Call<ResProducts> getCategoryProducts(@Query("category_id") String id);

    //Get all products
    @GET("api/products?fields=id,name,images,price")
    Call<ResProducts> getAllProuducts();


    //Get Product info by id
    @GET("api/products/{id}")
    Call<ResProducts> getProductInfo(@Path("id") String id);

    //Get Product info by ids
    @GET("api/products?fields=id,name,images,price")
    Call<ResProducts> getSpecificProducts (@Query("ids") String id);

    //Get Countries Kuwait Saudi
    @GET("api/countries?")
    Call<ResCountry> getCountries(@Query("ids") String id);

    //Get States by id
    @GET("api/states/{id}")
    Call<ResStates> getStates(@Path("id") String id);


    //Get vendors
    @GET("api/vendors/country/{countryid}/state/{stateid}")
    Call<ResVendors> getVendors(@Path("countryid") String country_id,@Path("stateid") String state_id);


    //Get Addresses
    @GET("api/vendors/address/{addressid}")
    Call<ResAddresses>getVendorAddresess(@Path("addressid") String addressId);


    //Filter Api
    @GET("api/products?fields=id,images,price,name")
    Call<ResProducts> getFilter(@Query("country")String country,
                                @Query("searchKeyword")String keyword,
                                @Query("state")String state,
                                @Query("district")String district,
                                @Query("category_id")String id,
                                @Query("vendor_name") String vendorName,
                                @Query("deliveredatthesameday")boolean deliveratsameday,
                                @Query("min_price")String minPrice);



   //Get districts
   @GET("api/districts/country/{country}/state/{state}")
   Call<Districts>getDestrics(@Path("country") String country,@Path("state") String state);



    //Add to shopping cart
    @POST("api/shopping_cart_items")
    Call<ResCartItems> addItemToCart(@Body Object item);

    //get shopping cart items
    @GET("api/shopping_cart_items/{customerId}")
    Call<ResCartItems> getCartItems(@Path("customerId") String id);

    //del shopping cart items
    @DELETE("api/shopping_cart_items/{id}")
    Call<ResCartItems> deleteCartItems(@Path("id") String id);

    //Login
    @POST("api/customers/login")
    Call<RegResponse> login(@Body Object item);

    //Add to wish List
    @POST("api/wishlist/add")
    Call<ResAddingWishList> addToWishList(@Query("productId") String id,
                                          @Query("shoppingCartTypeId") Integer type_id,
                                          @Query("quantity")Integer qty,
                                          @Query("customerId")Integer customerId);

    //Delete from wish list
    @POST("api/wishlist/delete")
    Call<ResAddingWishList>deleteWishlistItem(@Query("itemId")String id,@Query("customerId") String custId);


    //Get All WishList
    @GET("api/wishlist/{customerId}")
    Call<ResAddingWishList>getAllWishList(@Path("customerId") String id);

    //Register new Customer
    @POST("api/customers/")
    Call<RegResponse> regesterNewCustomer(@Body Object item);

    //verify phone number
    @POST("api/customers/PhoneVerification?")
    Call<VerificationCode>verifyMoblieNum(@Query("phoneno") String phone);

    //Retrieve Password
    @POST("api/customers/RetrievePassword/{email}/ ")
    Call<VerificationCode>retrivePassword(@Path("email") String email);

    //Place new Order
    @POST("/api/orders")
    Call<OrderResponse>placeNewOrder(@Body Object order);

    //Related products
    @GET("/api/products/related/{id}")
    Call<ResProducts> getRelatedProducts(@Path("id") String id);

    //Get discounted products with id
    @GET("/api/products/discounted/{id}")
    Call<ResProducts> getDiscountedProducts(@Path("id") String id);

    //Get discounted products all
    @GET("/api/products/discounted/")
    Call<ResProducts> getAllDiscountedProducts();

    //Update Customer
    @PUT("api/customers/{id}")
    Call<RegResponse> updateCustomer(@Body Object item,@Path("id")String id);


    //Select Present Card
    @POST("api/presentcard")
    Call<ResDeleteOrderItem> selectPresentCard(@Body Object item);


    //Add item to order
    @POST("api/orders/{orderid}/items")
    Call<ResUpdateOrder>addItemToOrder(@Path("orderid")String orderId,@Body Object order);


    //Update Order
    @PUT("api/orders/{orderId}/items/{orderItemId}")
    Call<ResUpdateOrder>updateOrder(@Body Object order,@Path("orderId")String orderId,@Path("orderItemId")String itemId);

    //Delete Item from order
    @DELETE("/api/orders/{orderId}/items/{orderItemId}")
    Call<ResDeleteOrderItem>deleteOrderItem(@Path("orderId")String orderId,@Path("orderItemId")String itemId);


    //Get vendor schedule intervals
    @GET("api/vendors/schedule/{VendorId}")
    Call<ResVendorsSch>getVendorSchedule(@Path("VendorId")String vendorId);


    //Request Payment Checkout
    @POST("api/payment/checkout")
    Call<ResCheckoutId> getCheckOutId(@Query("amount") int amount,@Query("currency") String currency);


}
