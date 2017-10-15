package sedra.appsmatic.com.sedra.Prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import sedra.appsmatic.com.sedra.API.Models.Customers.RegResponse;

/**
 * Created by Eng Ali on 4/20/2017.
 */
public class SaveSharedPreference {

    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_PASS = "password";
    static final String LANG_ID="langId";
    static final String lOAD_IMG_ID="imagesStatus";
    static final String CUSTOMER_ID="customerid";
    static final String WISHLIST_ORDERS="wishList";
    static final String CUSTOMER_INFO="customerInfo";
    static final String ORDER_ID="orderId";



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


    public static void setOrderId(Context context,String orderId){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(ORDER_ID,orderId);
        editor.commit();
    }

    public static String getOrderId(Context context){
        return getSharedPreferences(context).getString(ORDER_ID, "");
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
        editor.putString(CUSTOMER_ID, id);
        editor.commit();
    }


    public static void setCustomerInfo(Context context,RegResponse regResponse){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(regResponse);
        editor.putString(CUSTOMER_INFO, json);
        editor.commit();
    }


    public static RegResponse getCustomerInfo(Context context){

        String json= getSharedPreferences(context).getString(CUSTOMER_INFO, "");
        RegResponse regResponse=new RegResponse();
        if(!json.isEmpty()) {
            Type type = new TypeToken<RegResponse>() {}.getType();
            Gson gson = new Gson();
            regResponse= gson.fromJson(json, type);
        }
        return regResponse;
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

    public static void setWishListOrders(Context context,List<String> items){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString(WISHLIST_ORDERS, json);
        editor.commit();
    }



    public static List<String> getWishListOrders(Context context){
        String json= getSharedPreferences(context).getString(WISHLIST_ORDERS, "");
        List<String> cartMeals=new ArrayList<>();
        if(!json.isEmpty()) {
            Type type = new TypeToken<List<String>>() {}.getType();
            Gson gson = new Gson();
            cartMeals= gson.fromJson(json, type);
        }
        return cartMeals;
    }



}
