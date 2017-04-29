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

    public static PerformanceFragment newInstance() {
        return new PerformanceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_performance, container, false);
        connectbtn = (Button) view.findViewById(R.id.connectbtn);
        beginbtn = (Button) view.findViewById(R.id.beginbtn);
        imgv1 = (ImageView) view.findViewById(R.id.imgv1);
        imgv2 = (ImageView) view.findViewById(R.id.imgv2);
        imgv3 = (ImageView) view.findViewById(R.id.imgv3);
        imgv4 = (ImageView) view.findViewById(R.id.imgv4);
        connectbtn.setOnClickListener(this);
        beginbtn.setOnClickListener(this);
        imgv1.setOnClickListener(this);
        imgv2.setOnClickListener(this);
        imgv3.setOnClickListener(this);
        imgv4.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==connectbtn)
            Toast.makeText(getContext(), "Connecting...", Toast.LENGTH_SHORT).show();
        if(v==beginbtn)
            Toast.makeText(getContext(), "Beginning...", Toast.LENGTH_SHORT).show();
        if(v==imgv1)
            Toast.makeText(getContext(), "Just Run", Toast.LENGTH_SHORT).show();
        if(v==imgv2)
            Toast.makeText(getContext(), "Distance", Toast.LENGTH_SHORT).show();
        if(v==imgv3)
            Toast.makeText(getContext(), "Cardio", Toast.LENGTH_SHORT).show();
        if(v==imgv4)
            Toast.makeText(getContext(), "Time", Toast.LENGTH_SHORT).show();
    }
}
