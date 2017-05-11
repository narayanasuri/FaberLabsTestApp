package koolkat.faberlabstestapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Admin on 4/29/2017.
 */

public class PerformanceFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    Button connectbtn, beginbtn;
    ImageView imgv1, imgv2, imgv3, imgv4;
    int currentresource, resource, centerTile;

    public static PerformanceFragment newInstance() {
        return new PerformanceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        centerTile = 4;
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

    private void animateDiagonalPan() {
        AnimatorSet animSetXY = new AnimatorSet();

        float imgv1x = imgv1.getX();
        float imgv1y = imgv1.getY();

        float imgv2x = imgv2.getX();
        float imgv2y = imgv2.getY();

        float imgv3x = imgv3.getX();
        float imgv3y = imgv3.getY();

        float imgv4x = imgv4.getX();
        float imgv4y = imgv4.getY();

        ObjectAnimator imganim1a = ObjectAnimator.ofFloat(imgv1,
                "y", imgv3y);

        ObjectAnimator imganim1b = ObjectAnimator.ofFloat(imgv1,
                "x", imgv3x);

        ObjectAnimator imganim2a = ObjectAnimator.ofFloat(imgv2,
                "y", imgv2.getY(), imgv1y);

        ObjectAnimator imganim2b = ObjectAnimator.ofFloat(imgv2,
                "x", imgv2.getX(), imgv1x);

        ObjectAnimator imganim3a = ObjectAnimator.ofFloat(imgv3,
                "y", imgv3.getY(), imgv4y);

        ObjectAnimator imganim3b = ObjectAnimator.ofFloat(imgv3,
                "x", imgv3.getX(), imgv4x);

        ObjectAnimator imganim4a = ObjectAnimator.ofFloat(imgv4,
                "y", imgv4.getY(), imgv2y);

        ObjectAnimator imganim4b = ObjectAnimator.ofFloat(imgv4,
                "x", imgv4.getX(), imgv2x);

        animSetXY.playTogether(imganim1a, imganim1b, imganim2a, imganim2b, imganim3a, imganim3b, imganim4a, imganim4b);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(300);
        animSetXY.start();
    }

    private void animateDiagonalPan2() {
        AnimatorSet animSetXY = new AnimatorSet();

        float imgv1x = imgv1.getX();
        float imgv1y = imgv1.getY();

        float imgv2x = imgv2.getX();
        float imgv2y = imgv2.getY();

        float imgv3x = imgv3.getX();
        float imgv3y = imgv3.getY();

        float imgv4x = imgv4.getX();
        float imgv4y = imgv4.getY();

        ObjectAnimator imganim1a = ObjectAnimator.ofFloat(imgv1,
                "y", imgv2y);

        ObjectAnimator imganim1b = ObjectAnimator.ofFloat(imgv1,
                "x", imgv2x);

        ObjectAnimator imganim2a = ObjectAnimator.ofFloat(imgv2,
                "y", imgv2.getY(), imgv4y);

        ObjectAnimator imganim2b = ObjectAnimator.ofFloat(imgv2,
                "x", imgv2.getX(), imgv4x);

        ObjectAnimator imganim3a = ObjectAnimator.ofFloat(imgv3,
                "y", imgv3.getY(), imgv1y);

        ObjectAnimator imganim3b = ObjectAnimator.ofFloat(imgv3,
                "x", imgv3.getX(), imgv1x);

        ObjectAnimator imganim4a = ObjectAnimator.ofFloat(imgv4,
                "y", imgv4.getY(), imgv3y);

        ObjectAnimator imganim4b = ObjectAnimator.ofFloat(imgv4,
                "x", imgv4.getX(), imgv3x);

        animSetXY.playTogether(imganim1a, imganim1b, imganim2a, imganim2b, imganim3a, imganim3b, imganim4a, imganim4b);
        animSetXY.setInterpolator(new LinearInterpolator());
        animSetXY.setDuration(300);
        animSetXY.start();
    }

    private int evaluateCenterRight(int centerTile){
        //clockwise
        //integer corresponds to the respective imageview
        switch (centerTile){
            case 1:
                centerTile = 2;
                imgv1.bringToFront();
                imgv4.bringToFront();
                imgv2.bringToFront();
                break;
            case 2:
                centerTile = 4;
                imgv2.bringToFront();
                imgv3.bringToFront();
                imgv4.bringToFront();
                break;
            case 3:
                centerTile = 1;
                imgv3.bringToFront();
                imgv2.bringToFront();
                imgv1.bringToFront();
                break;
            case 4:
                centerTile = 3;
                imgv4.bringToFront();
                imgv1.bringToFront();
                imgv3.bringToFront();
                break;
        }
        return centerTile;
    }

    private int evaluateCenterLeft(int centerTile){
        //anti-clockwise
        //integer corresponds to the respective imageview
        switch (centerTile){
            case 1:
                centerTile = 3;
                imgv3.bringToFront();
                break;
            case 2:
                centerTile = 1;
                imgv1.bringToFront();
                break;
            case 3:
                centerTile = 4;
                imgv4.bringToFront();
                break;
            case 4:
                centerTile = 2;
                imgv2.bringToFront();
                break;
        }
        return centerTile;
    }

    @Override
    public void onClick(View v) {
        if (v == connectbtn)
            Toast.makeText(getContext(), "Connecting...", Toast.LENGTH_SHORT).show();
        if (v == beginbtn) {
            Toast.makeText(getContext(), "Beginning...", Toast.LENGTH_SHORT).show();
        }
        if (v == imgv1) {
            if(centerTile!=1) {
                if(centerTile==2) {
                    animateDiagonalPan2();
                    centerTile = evaluateCenterLeft(centerTile);
                }
                else if(centerTile==3){
                    animateDiagonalPan();
                    centerTile = evaluateCenterRight(centerTile);
                }
            }
            else{
                Toast.makeText(getContext(), "Just Run", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == imgv2) {
            if(centerTile!=2) {
                if(centerTile==1) {
                    animateDiagonalPan();
                    centerTile = evaluateCenterRight(centerTile);
                }
                else if(centerTile==4){
                    animateDiagonalPan2();
                    centerTile = evaluateCenterLeft(centerTile);
                }
            }
            else{
                Toast.makeText(getContext(), "Distance", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == imgv3) {
            if(centerTile!=3) {
                if(centerTile==4) {
                    animateDiagonalPan();
                    centerTile = evaluateCenterRight(centerTile);
                }
                else if(centerTile==1){
                    animateDiagonalPan2();
                    centerTile = evaluateCenterLeft(centerTile);
                }
            }
            else{
                Toast.makeText(getContext(), "Cardio", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == imgv4) {
            if(centerTile!=4) {
                if(centerTile==3) {
                    animateDiagonalPan2();
                    centerTile = evaluateCenterLeft(centerTile);
                }
                else if(centerTile==2){
                    animateDiagonalPan();
                    centerTile = evaluateCenterRight(centerTile);
                }
            }
            else{
                Toast.makeText(getContext(), "Time", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
