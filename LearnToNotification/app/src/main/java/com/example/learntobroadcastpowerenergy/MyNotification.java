package com.example.learntobroadcastpowerenergy;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

/*
 * Created by Bruce Dickson
 */

public class MyNotification extends NotificationCompat.Builder {

    private int ID;
    private String ContentTitle;
    private String ContentMessage;

    public MyNotification(Context context, int ID, String ContentTitle, String ContentMessage) {
        super(context,"");
        this.ID = ID;
        this.ContentTitle = ContentTitle;
        this.ContentMessage = ContentMessage;
    }

    public MyNotification(Context context, String ContentTitle, String ContentMessage) {
        super(context,"");
        this.ContentTitle = ContentTitle;
        this.ContentMessage = ContentMessage;
    }

    public int getID() {return ID;}

    public String getContentTitle() {
        return ContentTitle;
    }

    public String getContentMessage() {
        return ContentMessage;
    }

}

