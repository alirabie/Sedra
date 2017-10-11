package sedra.appsmatic.com.sedra.API.Models.PresentCards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 10/11/2017.
 */
public class Cardmessage {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("reciever")
    @Expose
    private String reciever;
    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("sender_phone_no")
    @Expose
    private String senderPhoneNo;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderPhoneNo() {
        return senderPhoneNo;
    }

    public void setSenderPhoneNo(String senderPhoneNo) {
        this.senderPhoneNo = senderPhoneNo;
    }
}
