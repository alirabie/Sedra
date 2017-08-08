package sedra.appsmatic.com.sedra.API.Models.verifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 8/8/2017.
 */
public class VerificationCode {
    @SerializedName("VerificationCode")
    @Expose
    private String verificationCode;

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

}
