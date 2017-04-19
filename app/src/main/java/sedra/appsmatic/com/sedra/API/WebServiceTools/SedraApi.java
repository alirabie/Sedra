package sedra.appsmatic.com.sedra.API.WebServiceTools;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sedra.appsmatic.com.sedra.API.Models.Categories.ResCategories;
import sedra.appsmatic.com.sedra.API.Models.Productes.Product;
import sedra.appsmatic.com.sedra.API.Models.Productes.ResProducts;

/**
 * Created by Eng Ali on 4/18/2017.
 */
public interface SedraApi {

    //Get All Categories
    @GET("api/categories?fields=id,name,description,image")
    Call<ResCategories> getCategories();

    //Get Category Products by Id
    @GET("api/products?fields=id,name,short_description,full_description,images")
    Call<ResProducts> getCategoryProducts(@Query("category_id") String id);

    //Get Product info by id
    @GET("api/products/{id}")
    Call<ResProducts> getProductInfo(@Path("id") String id);



}
