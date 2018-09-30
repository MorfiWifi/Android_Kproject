package com.apps.morfiwifi.morfi_project_samane.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

import com.apps.morfiwifi.morfi_project_samane.LoginActivity;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

public class MyTestReceiver extends ResultReceiver {
    private Receiver receiver;
    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public MyTestReceiver(Handler handler) {
        super(handler);
    }

    // Setter for assigning the receiver
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    // Defines our event interface for communication
    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }

    // Delegate method which passes the result to the receiver if the receiver has been assigned
    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (receiver != null) {
            receiver.onReceiveResult(resultCode, resultData);
            Log.d("SERVICE :" , "RECIVED IN TESTRECIVER");




           /* Intent intent = new Intent(this , LoginActivity.class);


//            build notification
// the addAction re-use the same intent to keep the example short
        Notification n  = new Notification.Builder()
                .setContentTitle("New mail from " + "test@gmail.com")
                .setContentText("Subject")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_account_billing, "Call", pIntent)
                .addAction(R.drawable.ic_3dot, "More", pIntent)
                .addAction(R.drawable.ic_account, "And more", pIntent).build();


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);*/
        }
    }
}
