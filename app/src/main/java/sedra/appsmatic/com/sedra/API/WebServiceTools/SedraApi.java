package sedra.appsmatic.com.sedra.API.WebServiceTools;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sedra.appsmatic.com.sedra.API.Models.Addresses.ResAddresses;
import sedra.appsmatic.com.sedra.API.Models.Categories.ResCategories;
import sedra.appsmatic.com.sedra.API.Models.Countries.ResCountry;
import sedra.appsmatic.com.sedra.API.Models.Customers.RegResponse;
import sedra.appsmatic.com.sedra.API.Models.Productes.Product;
import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;
import sedra.appsmatic.com.sedra.API.Models.ShoppingCart.ResCartItems;
import sedra.appsmatic.com.sedra.API.Models.States.ResStates;
import sedra.appsmatic.com.sedra.API.Models.Vendors.ResVendors;

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
                                @Query("category_id")String id,
                                @Query("vendor_name") String vendorName,
                                @Query("min_price")String minPrice);



    //Get districts
    //@GET("api/districts/country/{country}/state/{state}")



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

}
