package sedra.appsmatic.com.sedra.API.Models.ShoppingCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 6/14/2017.
 */
public class ReqCartItems {
    @SerializedName("shopping_cart_item")
    @Expose
    private ShoppingCartItem shoppingCartItem;

    public ShoppingCartItem getShoppingCartItem() {
        return shoppingCartItem;
    }

    public void setShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        this.shoppingCartItem = shoppingCartItem;
    }
}
