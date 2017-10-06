package com.abijayana.user.hutkrla;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 15-03-2017.
 */

public class foodadapter extends ArrayAdapter<food> {

    Context context;
    int resource;
    ArrayList<food> objects;



    public foodadapter(Context context, int resource, ArrayList<food> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoldera vw =new ViewHoldera();
        if(convertView==null){
            LayoutInflater lf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=lf.inflate(resource,null);
            vw.iv1=(ImageView)convertView.findViewById(R.id.imageView2) ;
            vw.tv1=(TextView)convertView.findViewById(R.id.textView2);
            vw.tv2=(TextView)convertView.findViewById(R.id.textView3);
            convertView.setTag(vw);


        }
        else vw=(ViewHoldera)convertView.getTag();
        vw.tv1.setText(objects.get(position).getNme());
        vw.tv2.setText(objects.get(position).getPrice());
        Picasso.with(getContext()).load(objects.get(position).getUrl()).into(vw.iv1);





        return  convertView;
    }

    public class ViewHoldera{

        TextView tv1;
        ImageView iv1;
        TextView tv2;


    }



}
