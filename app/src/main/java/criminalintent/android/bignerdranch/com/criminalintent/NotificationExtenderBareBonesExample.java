package criminalintent.android.bignerdranch.com.criminalintent;

import android.content.Context;
import android.content.Intent;

import com.onesignal.OSNotificationPayload;
import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationReceivedResult;
public class NotificationExtenderBareBonesExample extends NotificationExtenderService {
    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
        // Read properties from result.

        // Return true to stop the notification from displaying.
        boolean notifications = QueryPreferences.areNofificationsOn(this);
        return notifications;
    }
}

