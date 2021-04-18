package com.example.mobilelauncher;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    List<App> appList;

    public ViewPagerAdapter(Context context, List<App> appList){
        this.context = context;
        this.appList = appList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup singlePage=(ViewGroup) inflater.inflate(R.layout.view_viewpager,container,false);

        final GridView grid =singlePage.findViewById(R.id.grid_viewpager);
        grid.setAdapter(new GridAdapter(context, getEachPageApp().get(position)));
        container.addView(singlePage);

        return singlePage;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }

    @Override
    public int getCount() {
        int numOfPages=2;
        return numOfPages;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    public ArrayList<List<App>> getEachPageApp(){
        ArrayList<List<App>> listOfApps =new ArrayList<>();

        List<App> pageOne=appList.subList(0,16);
        List<App> pageTwo=appList.subList(17,26);

        listOfApps.add(pageOne);
        listOfApps.add(pageTwo);

        return listOfApps;
    }
}
