package criminalintent.android.bignerdranch.com.criminalintent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by User on 1/8/2017.
 */

public class ServiceRestartReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"Restarting service... " + intent.getAction());

        //boolean isOn = QueryPreferences.isAlarmOn(context);
        //PollService.setServiceAlarm(context,isOn);
    }
}
