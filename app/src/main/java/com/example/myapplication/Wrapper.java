package com.example.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

@TargetApi(Build.VERSION_CODES.O)
public class Wrapper {
    public static void startForegroundService(final Context context, final Intent intent) {
        context.startForegroundService(intent);
    }
}
