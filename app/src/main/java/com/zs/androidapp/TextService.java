package com.zs.androidapp;

import android.app.IntentService;
import android.content.Intent;


/**
 * 
 */

public class TextService extends IntentService {
    public static final String TAG = "TextService";

    public TextService() {
        super("TextService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

}