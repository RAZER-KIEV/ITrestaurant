package ua.kiev.netmaster.razer.itrestaurant.loger;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by RAZER on 07-Apr-16.
 */
public class L {
    public static String T="myLogs";
    public static void l(String s, Object obj){
        String str[]=obj.getClass().toString().split("\\.");
        String objName = str[str.length-1].trim();
        Log.d(T, objName + " " + s);
    }
    public static void l(String s){
        Log.d(T,s);
    }
    public static void t(String s, Context ctx){
        Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
    }
}

