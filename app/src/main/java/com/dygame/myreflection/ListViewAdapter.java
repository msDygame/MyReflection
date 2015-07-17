package com.dygame.myreflection;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/7/13.
 */
public class ListViewAdapter extends BaseAdapter
{
    protected LayoutInflater inflater;
    protected Context mContext ;
    protected ArrayList<String> sReflectionArray;
    public ListViewAdapter(Context context,  ArrayList<String> sArray)
    {
        mContext = context ;
        inflater = LayoutInflater.from(context);
        sReflectionArray = sArray ;
    }
    @Override
    public int getCount() { return sReflectionArray.size() ; }

    @Override
    public Object getItem(int position) { return sReflectionArray.get(position); }

    @Override
    public long getItemId(int position)
    {
        return position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            Log.e("MyCrashHandler", "ListView Adapter getView is null");
            convertView = inflater.inflate(R.layout.listview_item, null);
        }
        TextView txtItemName = (TextView)convertView.findViewById(R.id.textView1);
        ImageView ivItemIcon = (ImageView)convertView.findViewById(R.id.imageViewLogo);
        //
        txtItemName.setText((String) sReflectionArray.get(position) );
        String str = (String)sReflectionArray.get(position)  ;
        //
        if (str.equals("1")) ivItemIcon.setImageResource(R.drawable.imagesa);
        else if (str.equals("2")) ivItemIcon.setImageResource(R.drawable.imagesb) ;
        else if (str.equals("3")) ivItemIcon.setImageResource(R.drawable.imagesc) ;
        else if (str.equals("4")) ivItemIcon.setImageResource(R.drawable.imagesr) ;
        else if (str.equals("5")) ivItemIcon.setImageResource(R.drawable.imageso) ;
        else
            ivItemIcon.setImageResource(R.drawable.imagesa);
        Log.e("MyCrashHandler", "ListView Adapter getView"+position);
        return convertView;
    }
}
