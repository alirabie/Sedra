package sedra.appsmatic.com.sedra.API.Models.Categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 4/18/2017.
 */
public class CategoryImage {

    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("attachment")
    @Expose
    private Object attachment;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Object getAttachment() {
        return attachment;
    }

    public void setAttachment(Object attachment) {
        this.attachment = attachment;
    }
}
