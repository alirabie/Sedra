package sedra.appsmatic.com.sedra.API.Models.VendorDateSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 10/17/2017.
 */
public class Times {
    @SerializedName("2017-08-20T11:00:00")
    @Expose
    private String _20170820T110000;
    @SerializedName("2017-08-27T10:00:00")
    @Expose
    private String _20170827T100000;
    @SerializedName("2017-09-21T10:15:00")
    @Expose
    private String _20170921T101500;
    @SerializedName("2017-10-05T02:15:00")
    @Expose
    private String _20171005T021500;

    public String get20170820T110000() {
        return _20170820T110000;
    }

    public void set20170820T110000(String _20170820T110000) {
        this._20170820T110000 = _20170820T110000;
    }

    public String get20170827T100000() {
        return _20170827T100000;
    }

    public void set20170827T100000(String _20170827T100000) {
        this._20170827T100000 = _20170827T100000;
    }

    public String get20170921T101500() {
        return _20170921T101500;
    }

    public void set20170921T101500(String _20170921T101500) {
        this._20170921T101500 = _20170921T101500;
    }

    public String get20171005T021500() {
        return _20171005T021500;
    }

    public void set20171005T021500(String _20171005T021500) {
        this._20171005T021500 = _20171005T021500;
    }
}
