package com.weather.pc.searchengine.entity;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //GetKeyhash();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
    public void GetKeyhash(){
        try {
            PackageInfo info=getPackageManager().getPackageInfo("com.weather.pc.searchengine",
                    PackageManager.GET_SIGNATURES);
            for(Signature signature:info.signatures){
                try {
                    MessageDigest md=MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("keyhash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
