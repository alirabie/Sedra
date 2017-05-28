package sedra.appsmatic.com.sedra.PayFort;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Eng Ali on 5/28/2017.
 */
public class FortRequest extends com.payfort.sdk.android.dependancies.models.FortRequest implements Serializable {

    private Map<String,String> requestMap;
    private boolean showResponsePage;


    public Map<String, String> getRequestMap() {
        return requestMap;
    }
    public void setRequestMap(Map<String, String> requestMap) {
        this.requestMap = requestMap;
    }
    public boolean isShowResponsePage() {
        return showResponsePage;
    }
    public void setShowResponsePage(boolean showResponsePage) {
        this.showResponsePage = showResponsePage;
    }

}
