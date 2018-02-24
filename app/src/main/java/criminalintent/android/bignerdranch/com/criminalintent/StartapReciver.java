package criminalintent.android.bignerdranch.com.criminalintent;

/**
 * Created by GERMAN on 08-Dec-16.
 */


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by User on 11/29/2016.
 */

public class StartapReciver extends BroadcastReceiver {

    private static final String TAG = "StartupReciver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"Recived broadcast intent " + intent.getAction());

        //boolean isOn = QueryPreferences.isAlarmOn(context);
        //PollService.setServiceAlarm(context,isOn);
    }
}

