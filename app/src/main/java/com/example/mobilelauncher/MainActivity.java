package com.example.mobilelauncher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="Logger";
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        Log.i(TAG,"ON CREATE.");
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> appList = getApplicationContext().getPackageManager().queryIntentActivities(intent, 0);
        Log.i(TAG,"RETRIEVING APPLICATION LIST FROM PACKAGE MANAGER.. ");
        for(ResolveInfo currentApp : appList) {
            String appName = currentApp.activityInfo.loadLabel(getPackageManager()).toString();
            String appPackageName = currentApp.activityInfo.packageName;
            Drawable appImage = currentApp.activityInfo.loadIcon(getPackageManager());
            Log.i(TAG,"Application: "+appName);
        }

        setupViewPager();


    }


    public void setupViewPager(){
        viewPager= findViewById(R.id.viewpager);
        viewPagerAdapter=new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
    }


}