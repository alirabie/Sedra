package sedra.appsmatic.com.sedra.API.Models.ShoppingCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 6/14/2017.
 */
public class ResCartItems {
    @SerializedName("shopping_carts")
    @Expose
    private List<ShoppingCart> shoppingCarts = null;

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
}
