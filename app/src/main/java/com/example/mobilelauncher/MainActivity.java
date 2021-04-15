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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        setupViewPager();
        setupBottomSheet();

    }

    public List<ResolveInfo> getBottomAppList(){
        List<ResolveInfo> collapsedBottomList=getAppList().subList(0,8);
        return collapsedBottomList;
    }

    public List<ResolveInfo> getAppList(){
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> appList = getApplicationContext().getPackageManager().queryIntentActivities(intent, 0);
        return appList;
    }

    public void setupViewPager(){
        viewPager= findViewById(R.id.viewpager);
        List<ResolveInfo> appList=getAppList();
        viewPagerAdapter=new ViewPagerAdapter(this,appList);
        viewPager.setAdapter(viewPagerAdapter);
    }

    public void setupBottomSheet(){
        final View bottomsheet=findViewById(R.id.bottomsheet);
        bottomSheetBehavior=BottomSheetBehavior.from(bottomsheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setPeekHeight(400);
        bottomSheetBehavior.setHideable(false);

        final GridView bottomGrid=findViewById(R.id.bottomGrid);
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


}