package sedra.appsmatic.com.sedra.API.Models.PresentCards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 10/11/2017.
 */
public class OrderCard {
    @SerializedName("cards")
    @Expose
    private List<Card> cards = null;


    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

}
