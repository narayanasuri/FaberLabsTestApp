package koolkat.faberlabstestapp;

import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Admin on 4/29/2017.
 */

public class PerformanceFragment  extends android.support.v4.app.Fragment implements View.OnClickListener {

    Button connectbtn, beginbtn;
    ImageView imgv1, imgv2, imgv3, imgv4;
    int currentresource, resource;

    public static PerformanceFragment newInstance() {
        return new PerformanceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_performance, container, false);
        connectbtn = (Button) view.findViewById(R.id.connectbtn);
        beginbtn = (Button) view.findViewById(R.id.beginbtn);
        imgv1 = (ImageView) view.findViewById(R.id.imgv1);
        imgv1.setTag(R.drawable.runbox);
        imgv2 = (ImageView) view.findViewById(R.id.imgv2);
        imgv2.setTag(R.drawable.distancebox);
        imgv3 = (ImageView) view.findViewById(R.id.imgv3);
        imgv3.setTag(R.drawable.cardiobox);
        imgv4 = (ImageView) view.findViewById(R.id.imgv4);
        imgv4.setTag(R.drawable.timebox);
        connectbtn.setOnClickListener(this);
        beginbtn.setOnClickListener(this);
        imgv1.setOnClickListener(this);
        imgv2.setOnClickListener(this);
        imgv3.setOnClickListener(this);
        imgv4.setOnClickListener(this);
        return view;
    }

    private void rotate(int currentresource){
        resource = R.drawable.runbox;
        if(currentresource == resource) {
            imgv1.setImageResource(R.drawable.timebox);
            imgv1.setTag(R.drawable.timebox);
            imgv2.setImageResource(R.drawable.cardiobox);
            imgv2.setTag(R.drawable.cardiobox);
            imgv3.setImageResource(R.drawable.distancebox);
            imgv3.setTag(R.drawable.distancebox);
            imgv4.setImageResource(R.drawable.runbox);
            imgv4.setTag(R.drawable.runbox);
        }
        resource = R.drawable.timebox;
        if(currentresource == resource) {
            imgv1.setImageResource(R.drawable.runbox);
            imgv1.setTag(R.drawable.runbox);
            imgv2.setImageResource(R.drawable.distancebox);
            imgv2.setTag(R.drawable.distancebox);
            imgv3.setImageResource(R.drawable.cardiobox);
            imgv3.setTag(R.drawable.cardiobox);
            imgv4.setImageResource(R.drawable.timebox);
            imgv4.setTag(R.drawable.timebox);
        }
        resource = R.drawable.distancebox;
        if(currentresource == resource) {
            imgv1.setImageResource(R.drawable.cardiobox);
            imgv1.setTag(R.drawable.cardiobox);
            imgv2.setImageResource(R.drawable.runbox);
            imgv2.setTag(R.drawable.runbox);
            imgv3.setImageResource(R.drawable.timebox);
            imgv3.setTag(R.drawable.timebox);
            imgv4.setImageResource(R.drawable.distancebox);
            imgv4.setTag(R.drawable.distancebox);
        }
        resource = R.drawable.cardiobox;
        if(currentresource == resource) {
            imgv1.setImageResource(R.drawable.distancebox);
            imgv1.setTag(R.drawable.distancebox);
            imgv2.setImageResource(R.drawable.timebox);
            imgv2.setTag(R.drawable.timebox);
            imgv3.setImageResource(R.drawable.runbox);
            imgv3.setTag(R.drawable.runbox);
            imgv4.setImageResource(R.drawable.cardiobox);
            imgv4.setTag(R.drawable.cardiobox);
        }
    }

    @Override
    public void onClick(View v) {
        if(v==connectbtn)
            Toast.makeText(getContext(), "Connecting...", Toast.LENGTH_SHORT).show();
        if(v==beginbtn) {
            Toast.makeText(getContext(), "Beginning...", Toast.LENGTH_SHORT).show();
        }
        if(v==imgv1) {
            currentresource = (Integer) imgv1.getTag();
            rotate(currentresource);
        }
        if(v==imgv2) {
            currentresource = (Integer) imgv2.getTag();
            rotate(currentresource);
        }
        if(v==imgv3){
            currentresource = (Integer) imgv3.getTag();
            rotate(currentresource);
        }
        if(v==imgv4) {
            currentresource = (Integer) imgv4.getTag();
            resource = R.drawable.runbox;
            if(currentresource == resource)
                Toast.makeText(getContext(), "Just Run", Toast.LENGTH_SHORT).show();
            resource = R.drawable.timebox;
            if(currentresource == resource)
                Toast.makeText(getContext(), "Time", Toast.LENGTH_SHORT).show();
            resource = R.drawable.cardiobox;
            if(currentresource == resource)
                Toast.makeText(getContext(), "Cardio", Toast.LENGTH_SHORT).show();
            resource = R.drawable.distancebox;
            if(currentresource == resource)
                Toast.makeText(getContext(), "Distance", Toast.LENGTH_SHORT).show();
        }
    }
}
