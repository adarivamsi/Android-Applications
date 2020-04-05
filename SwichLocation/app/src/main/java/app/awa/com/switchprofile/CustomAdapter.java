package app.awa.com.switchprofile;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomAdapter extends BaseAdapter{
Context context;
    List<app.awa.com.switchprofile.EmpInfo> list;
    public CustomAdapter(app.awa.com.switchprofile.Main2Activity main2Activity, List<app.awa.com.switchprofile.EmpInfo> list) {
        this.list=list;
        this.context=main2Activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.custom_adapter,null);
        TextView tv=(TextView)convertView.findViewById(R.id.textView);
        app.awa.com.switchprofile.EmpInfo info=list.get(position);
        tv.setText(info.getLati()+","+info.getLongi());
        Button btn=(Button)convertView.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(context);
                Button rbtn=(Button)dialog.findViewById(R.id.button3);
                Button vbtn=(Button)dialog.findViewById(R.id.button4);
                Button sbtn=(Button)dialog.findViewById(R.id.button5);
                rbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"ring",Toast.LENGTH_LONG).show();
                    }
                });
                vbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"ring",Toast.LENGTH_LONG).show();
                    }
                });
                sbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"ring",Toast.LENGTH_LONG).show();
                    }
                });
                dialog.show();
            }
        });
        return convertView;
    }
}