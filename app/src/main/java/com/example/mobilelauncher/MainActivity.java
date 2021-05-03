package com.example.mobilelauncher;

import androidx.annotation.NonNull;
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
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    BottomSheetBehavior bottomSheetBehavior;
    GridView bottomGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        setupViewPager();
        setupBottomSheet();

    }

    public List<App> createAppList(List<ResolveInfo> appList, int bottomNum){
        List<App> newList=new ArrayList<>();
        if(bottomNum!=0){
            for (int i = 0; i < 4; i++) {
                newList.add(new App(appList.get(i).activityInfo.loadLabel(getPackageManager()).toString() , appList.get(i).activityInfo.packageName , appList.get(i).activityInfo.loadIcon(getPackageManager())));
            }
        }else {
            for (int i = 0; i < appList.size(); i++) {
                newList.add(new App(appList.get(i).activityInfo.loadLabel(getPackageManager()).toString() , appList.get(i).activityInfo.packageName , appList.get(i).activityInfo.loadIcon(getPackageManager())));
            }
        }
        return newList;
    }

    public List<App> getBottomAppList(){
        List<ResolveInfo> collapsedBottomList=getAppList().subList(0,4);
        List<App> newList=createAppList(collapsedBottomList,4);

        for(int i=0; i<4;i++){
            newList.add(new App("App" + i, "com.android.App" + i, getResources().getDrawable(R.mipmap.ic_launcher) ));
        }

        return newList;
    }

    public List<ResolveInfo> getAppList(){
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> appList = getApplicationContext().getPackageManager().queryIntentActivities(intent, 0);
        return appList;
    }

    public void setupViewPager(){
        viewPager= findViewById(R.id.viewpager);
        List<App> newList=createAppList(getAppList(),0);
        viewPagerAdapter=new ViewPagerAdapter(this,newList);
        viewPager.setAdapter(viewPagerAdapter);
    }

    public void setupBottomSheet(){
        final View bottomsheet=findViewById(R.id.bottomsheet);
        bottomSheetBehavior=BottomSheetBehavior.from(bottomsheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setPeekHeight(400);
        bottomSheetBehavior.setHideable(false);

        bottomGrid=findViewById(R.id.bottomGrid);
        bottomGrid.setAdapter(new GridAdapter(this,getBottomAppList()));
        int[] locationOnScreen = new int[]{0,0};

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                switch (newState){
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.i("STATE"," DRAGGING...");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.i("STATE"," EXPANDED...");
                        break;
                    case BottomSheetBehavior.STATE_HALF_EXPANDED:
                        Log.i("STATE"," HALFEXPANDED...");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                         Log.i("STATE","COLLAPSED...");
                        break;
                }
            }


            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                bottomGrid.getLocationOnScreen(locationOnScreen);
                Log.d("BottomSheet Location", "X "+ locationOnScreen[0]+" Y:"+locationOnScreen[1]+" ");
            }
        });

    }

    public void itemPress(App app){
        Intent launchApp=getApplicationContext().getPackageManager().getLaunchIntentForPackage(app.getPackageName());
        if(launchApp!=null){
            getApplicationContext().startActivity(launchApp);
        }
    }

}