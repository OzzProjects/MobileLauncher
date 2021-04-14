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
import android.widget.GridView;
import android.widget.ImageButton;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="Logger";
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
        List<ResolveInfo> collapsedBottomList=getAppList().subList(0,4);
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
        bottomSheetBehavior.setPeekHeight(400);
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        final GridView bottomGrid=findViewById(R.id.bottomGrid);
        bottomGrid.setAdapter(new GridAdapter(this,getBottomAppList()));

    }


}