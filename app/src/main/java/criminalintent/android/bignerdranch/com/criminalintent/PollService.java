package criminalintent.android.bignerdranch.com.criminalintent;

/**
 * Created by GERMAN on 08-Dec-16.
 */

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/28/2016.
 */

public class PollService extends IntentService {
    private static final String TAG = "PollService";
    public static final String ACTION_SHOW_NOTIFICATION
            = "com.example.user.photogallery.PollService.SHOW_NOTIFICATON";
    public static final String ACTION_RESTART_NOTIFICATIONS
            = "com.example.user.photogallery.PollService.RESTART_NOTIFICATON";
    public static final String PERM_PRIVATE = "com.bignerdranch.android.photogallery.PRIVATE";
    public static final String PERM_PRIVATE_RESTART = "com.bignerdranch.android.photogallery.PRIVATE_RESTART";
    public static final String REQUEST_CODE = "REQUEST_CODE";
    public static final String NOTIFICATION = "NOTIFICATION";

    private static final long POLL_INTERVAL = 60*1000;

    private ArrayList Data_Fragment1 = new ArrayList();
    private ArrayList Data_Fragment2 = new ArrayList();
    private ArrayList Data_Fragment3 = new ArrayList();
    private ArrayList Data_Fragment4 = new ArrayList();
    private ArrayList mcategories = new ArrayList();
    private ArrayList murl_categories = new ArrayList();

    public static Intent newInstance(Context context){
        return new Intent(context,PollService.class);
    }

    public static void setServiceAlarm(Context context,boolean isOn){
        Intent i = PollService.newInstance(context);
        PendingIntent pi = PendingIntent.getService(context,0,i,0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if(isOn){
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime(),POLL_INTERVAL,pi);
        } else {
            alarmManager.cancel(pi);
            pi.cancel();
        }

        //QueryPreferences.setAlarmOn(context,isOn);

    }

    public static boolean isServiceAlarmOn(Context context){
        Intent i = PollService.newInstance(context);
        PendingIntent pi = PendingIntent.getService(context,0,i,PendingIntent.FLAG_NO_CREATE);
        return pi != null;
    }

    public PollService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(!isNetworkAvaiableAndConnected()){
            return;
        }
        Log.i(TAG,"Recived an intent: " + intent);

        //String query = QueryPreferences.getStoredQuery(this);
        //String lastResultId = QueryPreferences.getLastResultId(this);
        List<category_page> items;

        /*
        if(query == null){
            items = new CategoryFetchr().fetchItems("http://www.shekulli.com.al/api/efundit.php");
        } else {
            items = new CategoryFetchr().fetchItems("http://www.shekulli.com.al/api/efundit.php");
        }*/

        items = new CategoryFetchr().fetchItems("http://www.shekulli.com.al/api/efundit.php");

        if(items.size()==0){
            return;
        }

        String resultId = items.get(0).getMtitle_post();
        String resultUrl = "https" + items.get(0).getMurl_post().substring(4);


    }

    private boolean isNetworkAvaiableAndConnected(){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isNetworkAvaiable = cm.getActiveNetworkInfo() != null;
        boolean isNetworkConnected = isNetworkAvaiable && cm.getActiveNetworkInfo().isConnected();

        return isNetworkConnected;
    }

    private void showBackgroundNotification(int requestCode,Notification notification){
        Intent i = new Intent(ACTION_SHOW_NOTIFICATION);
        i.putExtra(REQUEST_CODE,requestCode);
        i.putExtra(NOTIFICATION,notification);
        sendOrderedBroadcast(i,PERM_PRIVATE,null,null,
                Activity.RESULT_OK,null,null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent i = new Intent(ACTION_RESTART_NOTIFICATIONS);
        sendBroadcast(i,PERM_PRIVATE_RESTART);
    }
}
