package com.example.mobilelauncher;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {
    Context context;
    List<App> appList;


    public GridAdapter(Context context, List<App> appList) {
        this.context = context;
        this.appList = appList;
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return appList.indexOf(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_app, parent, false);
        }
        ImageView image = convertView.findViewById(R.id.image);
        TextView name =convertView.findViewById(R.id.name);
        LinearLayout layout=convertView.findViewById(R.id.app_layout);

        image.setImageDrawable(appList.get(position).getImage());
        name.setText(appList.get(position).getName());


        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).itemPress(appList.get(position));
            }
        });


        return convertView;
    }





}
