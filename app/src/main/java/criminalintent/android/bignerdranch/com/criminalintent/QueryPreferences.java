package criminalintent.android.bignerdranch.com.criminalintent;

/**
 * Created by GERMAN on 08-Dec-16.
 */

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by User on 11/27/2016.
 */

public class QueryPreferences {
    private static final String PREF_ARE_NOTIFICATIONS_ON = "are_notifications_on";


    public static boolean areNofificationsOn(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(PREF_ARE_NOTIFICATIONS_ON,false);
    }

    public static void setNotificationsOn(Context context,boolean isOn){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(PREF_ARE_NOTIFICATIONS_ON,isOn)
                .apply();
    }
}
