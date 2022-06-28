package com.zs.androidapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 使用androidx ActivityResultCaller跳转activity并获取返回结果
 * 使用此种方法获取activity跳转结果必须实现了ActivityResultCaller接口
 */

class CallbackActivity extends ComponentActivity {
    @Override
    protected void onCreate(@Nullable @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册activity，获取到activityResultLauncher(此方法必须在started之前)
        ActivityResultLauncher<String> resultLauncher =
                registerForActivityResult(new ActivityResultContracts.GetContent(),
                        new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {

            }
        });

        /*
        StartActivityForResult可以获取到完整的返回结果
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                int resultCode = result.getResultCode();
                if (resultCode == RESULT_OK){
                    Intent intent = result.getData();
                }
            }
        });*/

        //启动activity
        resultLauncher.launch("image/*");
        //resultLauncher.launch(new Intent(this, MainActivity.class));
    }
}

class CallbackResultContract extends ActivityResultContract<String, String> {

    @NonNull
    @android.support.annotation.NonNull
    @Override
    public Intent createIntent(@NonNull @android.support.annotation.NonNull Context context, String input) {
        return new Intent()
                .addCategory("")
                .setType(input);
    }

    @Override
    public String parseResult(int resultCode, @Nullable @android.support.annotation.Nullable Intent intent) {
        if (intent == null || resultCode != Activity.RESULT_OK){
            return null;
        }
        return intent.getData().toString();
    }
}
