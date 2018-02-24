package criminalintent.android.bignerdranch.com.criminalintent;

/**
 * Created by GERMAN on 08-Dec-16.
 */


import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.Date;

/**
 * Created by User on 11/29/2016.
 */

public class NotificationReciver extends BroadcastReceiver {
    private static final String TAG = "NotificationReciver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"recived intent " + intent.getAction());
        if(getResultCode() != Activity.RESULT_OK){
            return;
        }

        int requestCode = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        Notification notification = intent.getParcelableExtra(PollService.NOTIFICATION);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(requestCode,notification);
    }
}