package sedra.appsmatic.com.sedra.API.Models.VendorDateSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 10/17/2017.
 */
public class Deliveryschedule {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("vendorid")
    @Expose
    private Integer vendorid;
    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("times")
    @Expose
    private Times times;
    @SerializedName("timefrom")
    @Expose
    private String timefrom;
    @SerializedName("timeto")
    @Expose
    private String timeto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVendorid() {
        return vendorid;
    }

    public void setVendorid(Integer vendorid) {
        this.vendorid = vendorid;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Times getTimes() {
        return times;
    }

    public void setTimes(Times times) {
        this.times = times;
    }

    public String getTimefrom() {
        return timefrom;
    }

    public void setTimefrom(String timefrom) {
        this.timefrom = timefrom;
    }

    public String getTimeto() {
        return timeto;
    }

    public void setTimeto(String timeto) {
        this.timeto = timeto;
    }
}
