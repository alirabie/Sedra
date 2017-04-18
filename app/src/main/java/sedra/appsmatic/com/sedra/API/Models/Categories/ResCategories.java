package sedra.appsmatic.com.sedra.API.Models.Categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 4/18/2017.
 */
public class ResCategories {
    @SerializedName("categories")
    @Expose
    private List<Categori> categories = null;

    public List<Categori> getCategories() {
        return categories;
    }

    public void setCategories(List<Categori> categories) {
        this.categories = categories;
    }
}
