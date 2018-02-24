package criminalintent.android.bignerdranch.com.criminalintent;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by GERMAN on 12-Mar-17.
 */

public class MyApplication extends Application {

    private ArrayList Data_Fragment1 = new ArrayList();
    private ArrayList Data_Fragment2 = new ArrayList();
    private ArrayList Data_Fragment3 = new ArrayList();
    private ArrayList Data_Fragment4 = new ArrayList();
    private ArrayList mcategories = new ArrayList();
    private ArrayList murl_categories = new ArrayList();

    private void addShortcutIcon() {
        Intent shortcutintent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        shortcutintent.putExtra("duplicate", false);
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Shekulli");
        Parcelable icon = Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.mipmap.logoportokallishekulli);
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        shortcutintent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(getApplicationContext(), SplashScreen.class));
        sendBroadcast(shortcutintent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .init();

        // Call syncHashedEmail anywhere in your app if you have the user's email.
        // This improves the effectiveness of OneSignal's "best-time" notification scheduling feature.
        // OneSignal.syncHashedEmail(userEmail);
    }

    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        // This fires when a notification is opened by tapping on it.
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            OSNotificationAction.ActionType actionType = result.action.type;
            JSONObject data = result.notification.payload.additionalData;
            //String customKey;
            //String gg;
            String url = "";
            String cat_title = "";
            String cat_url = "";
            String share = "";

            Log.i("OneSignalExample", "we are in");
            Log.i("OneSignalExample", "open");

            if (data != null) {
                url = data.optString("url", null);
                cat_title = data.optString("cat_title", null);
                cat_url = data.optString("cat_url", null);
                share = data.optString("share", null);
                if (url != null)
                    Log.i("OneSignalExample", "url set with value: " + url);
                if (cat_title != null)
                    Log.i("OneSignalExample", "cat_title set with value: " + cat_title);
                if (cat_url != null)
                    Log.i("OneSignalExample", "cat_url set with value: " + cat_url);
                if (share != null)
                    Log.i("OneSignalExample", "share set with value: " + share);

            }

            if (actionType == OSNotificationAction.ActionType.ActionTaken)

                Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);


            // The following can be used to open an Activity of your choice.
            // Replace - getApplicationContext() - with any Android Context.
            Intent intent = PostActivity.newIntent(getApplicationContext(), url, "Lajm i Fundit", cat_url, share, Data_Fragment1, Data_Fragment2, Data_Fragment3, Data_Fragment4, mcategories, murl_categories);

            //Intent intent = new Intent(getApplicationContext(), YourActivity.class);
             intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
             startActivity(intent);

            // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
            //   if you are calling startActivity above.
     /*
        <application ...>
          <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
        </application>
     */
        }
    }

    public static Context getAppContext() {
        return getAppContext();
    }
}