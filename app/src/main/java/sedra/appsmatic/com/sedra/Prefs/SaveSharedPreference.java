package sedra.appsmatic.com.sedra.Prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Eng Ali on 4/20/2017.
 */
public class SaveSharedPreference {

    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_PASS = "password";
    static final String LANG_ID="langId";
    static final String lOAD_IMG_ID="imagesStatus";
    static final String CUSTOMER_ID="customerid";


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.putString(PREF_USER_PASS, password);
        editor.commit();
    }

    public static void setLangId(Context context,String lang){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LANG_ID,lang);
        editor.commit();
    }

    public static String getLangId(Context context){
        return getSharedPreferences(context).getString(LANG_ID, "");
    }




    public static Boolean getImgLoadingSatatus(Context context){
        return getSharedPreferences(context).getBoolean(lOAD_IMG_ID, true);
    }


    public static void setImgLoadStatus(Context context,Boolean status){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(lOAD_IMG_ID,status);
        editor.commit();
    }


    public static void setCustomerId(Context context,String id){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(CUSTOMER_ID,id);
        editor.commit();
    }

    public static String getCustomerId(Context context){
        return getSharedPreferences(context).getString(CUSTOMER_ID, "");
    }

    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getUserPassword(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_PASS, "");
    }

    public static void clearUserName(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }

}
