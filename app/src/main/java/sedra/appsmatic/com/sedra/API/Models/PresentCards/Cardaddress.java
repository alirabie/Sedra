package sedra.appsmatic.com.sedra.API.Models.PresentCards;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 10/11/2017.
 */
public class Cardaddress {
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("building_no")
    @Expose
    private String buildingNo;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("reciever_phone_no")
    @Expose
    private String recieverPhoneNo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRecieverPhoneNo() {
        return recieverPhoneNo;
    }

    public void setRecieverPhoneNo(String recieverPhoneNo) {
        this.recieverPhoneNo = recieverPhoneNo;
    }
}
