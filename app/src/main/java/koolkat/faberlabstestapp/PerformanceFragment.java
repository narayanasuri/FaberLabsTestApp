package koolkat.faberlabstestapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ikovac.timepickerwithseconds.MyTimePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Admin on 4/29/2017.
 */

public class PerformanceFragment extends android.support.v4.app.Fragment implements View.OnClickListener, MyTimePickerDialog.OnTimeSetListener {

    Button connectbtn, beginbtn;
    ImageView imgv1, imgv2, imgv3, imgv4;
    int centerTile;
    LinearLayout runLayout, cardioLayout, timeLayout, distanceLayout, tilesLayout;
    RelativeLayout cardiopanelType, cardiopanelFeedback, cardiopanelLocation, tilesRelativeLayout;
    RelativeLayout timepanelTime, timepanelFeedback, timepanelLocation;
    RelativeLayout distancepanelDistance, distancepanelFeedback, distancepanelLocation, distancepanelTime;
    RelativeLayout runpanelDistance, runpanelFeedback;
    TextView cardioPanelTypetv, cardioPanelLocationtv;
    TextView timePanelTimetv, timePanelLocationtv;
    TextView distancePanelDistancetv, distancePanelLocationtv, distancePanelTimetv;
    TextView runPanelDistancetv, runPanelFeedbacktv;
    Calendar dateTime;
    SimpleDateFormat sdf;

    private String[] cardioPanelTypeArray = {"Endurance", "Strength"};
    private int indexCardioPanelType=0;

    private String[] cardioPanelLocationArray = {"Indoor", "Outdoor"};
    private int indexCardioPanelLocation=0;

    private String[] timePanelLocationArray = {"Indoor", "Outdoor"};
    private int indexTimePanelLocation=1;

    private String[] distancePanelLocationArray = {"Indoor", "Outdoor"};
    private int indexDistancePanelLocation=1;

    String cardioPanelTypeValue="Endurance", cardioPanelLocationValue="Indoor";
    String timePanelTimeValue="00:30:12", timePanelLocationValue="Outdoor";
    String distancePanelDistanceValue="18", distancePanelLocationValue="Outdoor", distancePanelTimeValue="00:14:00";
    String runPanelDistanceValue="18";

    public static PerformanceFragment newInstance() {
        return new PerformanceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        centerTile = 4;
        View view = inflater.inflate(R.layout.fragment_performance, container, false);

        dateTime = Calendar.getInstance();
        sdf = new SimpleDateFormat("HH:mm:ss");

        tilesRelativeLayout = (RelativeLayout) view.findViewById(R.id.tiles_relative_layout);
        tilesRelativeLayout.setOnTouchListener(new GestureHelper(getContext()){

            @Override
            public void onSwipeLeft() {
                animateDiagonalPan();
                centerTile = evaluateCenterRight(centerTile);
            }

            @Override
            public void onSwipeRight() {
                animateDiagonalPan2();
                centerTile = evaluateCenterLeft(centerTile);
            }
        });

        tilesLayout = (LinearLayout) view.findViewById(R.id.tiles_layout);

        runLayout = (LinearLayout) view.findViewById(R.id.run_panel);
        cardioLayout = (LinearLayout) view.findViewById(R.id.cardio_panel);
        timeLayout = (LinearLayout) view.findViewById(R.id.time_panel);
        distanceLayout = (LinearLayout) view.findViewById(R.id.distance_panel);


        cardiopanelType = (RelativeLayout) view.findViewById(R.id.cardiopanel_type);
        cardiopanelType.setOnClickListener(this);
        cardioPanelTypetv = (TextView) view.findViewById(R.id.cardiopanel_typetv);
        cardiopanelFeedback = (RelativeLayout) view.findViewById(R.id.cardiopanel_feedback);
        cardiopanelFeedback.setOnClickListener(this);
        cardiopanelLocation = (RelativeLayout) view.findViewById(R.id.cardiopanel_location);
        cardiopanelLocation.setOnClickListener(this);
        cardioPanelLocationtv = (TextView) view.findViewById(R.id.cardiopanel_locationtv);

        timepanelTime = (RelativeLayout) view.findViewById(R.id.timepanel_time);
        timepanelTime.setOnClickListener(this);
        timePanelTimetv = (TextView) view.findViewById(R.id.timepanel_timetv);
        timepanelFeedback = (RelativeLayout) view.findViewById(R.id.timepanel_feedback);
        timepanelFeedback.setOnClickListener(this);
        timepanelLocation = (RelativeLayout) view.findViewById(R.id.timepanel_location);
        timepanelLocation.setOnClickListener(this);
        timePanelLocationtv = (TextView) view.findViewById(R.id.timepanel_locationtv);

        distancepanelTime = (RelativeLayout) view.findViewById(R.id.distancepanel_time);
        distancepanelTime.setOnClickListener(this);
        distancePanelTimetv = (TextView) view.findViewById(R.id.distancepanel_timetv);
        distancepanelDistance = (RelativeLayout) view.findViewById(R.id.distancepanel_distance);
        distancepanelDistance.setOnClickListener(this);
        distancePanelDistancetv = (TextView) view.findViewById(R.id.distancepanel_distancetv);
        distancepanelFeedback = (RelativeLayout) view.findViewById(R.id.distancepanel_feedback);
        distancepanelFeedback.setOnClickListener(this);
        distancepanelLocation = (RelativeLayout) view.findViewById(R.id.distancepanel_location);
        distancepanelLocation.setOnClickListener(this);
        distancePanelLocationtv = (TextView) view.findViewById(R.id.distancepanel_locationtv);

        runpanelDistance = (RelativeLayout) view.findViewById(R.id.runpanel_distance);
        runpanelDistance.setOnClickListener(this);
        runPanelDistancetv = (TextView) view.findViewById(R.id.runpanel_distancetv);
        runpanelFeedback = (RelativeLayout) view.findViewById(R.id.runpanel_feedback);
        runpanelFeedback.setOnClickListener(this);
        runPanelFeedbacktv = (TextView) view.findViewById(R.id.runpanel_feedbacktv);

        connectbtn = (Button) view.findViewById(R.id.connectbtn);
        beginbtn = (Button) view.findViewById(R.id.beginbtn);
        beginbtn.setOnClickListener(this);
        imgv1 = (ImageView) view.findViewById(R.id.imgv1);
        imgv1.setTag(R.drawable.runbox);
        imgv2 = (ImageView) view.findViewById(R.id.imgv2);
        imgv2.setTag(R.drawable.distancebox);
        imgv2.bringToFront();
        imgv3 = (ImageView) view.findViewById(R.id.imgv3);
        imgv3.setTag(R.drawable.cardiobox);
        imgv3.bringToFront();
        imgv4 = (ImageView) view.findViewById(R.id.imgv4);
        imgv4.setTag(R.drawable.timebox);
        imgv4.bringToFront();
        connectbtn.setOnClickListener(this);

        imgv1.setOnTouchListener(new GestureHelper(getContext()){

            @Override
            public void onSwipeLeft() {
                animateDiagonalPan();
                centerTile = evaluateCenterRight(centerTile);
            }

            @Override
            public void onSwipeRight() {
                animateDiagonalPan2();
                centerTile = evaluateCenterLeft(centerTile);
            }

            @Override
            public void onClick() {
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
            }
        });

        imgv2.setOnTouchListener(new GestureHelper(getContext()){

            @Override
            public void onSwipeLeft() {
                animateDiagonalPan();
                centerTile = evaluateCenterRight(centerTile);
            }

            @Override
            public void onSwipeRight() {
                animateDiagonalPan2();
                centerTile = evaluateCenterLeft(centerTile);
            }

            @Override
            public void onClick() {
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
            }
        });

        imgv3.setOnTouchListener(new GestureHelper(getContext()){

            @Override
            public void onSwipeLeft() {
                animateDiagonalPan();
                centerTile = evaluateCenterRight(centerTile);
            }

            @Override
            public void onSwipeRight() {
                animateDiagonalPan2();
                centerTile = evaluateCenterLeft(centerTile);
            }

            @Override
            public void onClick() {
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
            }
        });

        imgv4.setOnTouchListener(new GestureHelper(getContext()){

            @Override
            public void onSwipeLeft() {
                animateDiagonalPan();
                centerTile = evaluateCenterRight(centerTile);
            }

            @Override
            public void onSwipeRight() {
                animateDiagonalPan2();
                centerTile = evaluateCenterLeft(centerTile);
            }

            @Override
            public void onClick() {
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
            }
        });

        return view;
    }

    private void animateDiagonalPan() {
        AnimatorSet animSetXY = new AnimatorSet();
        AnimatorSet animSetXYa = new AnimatorSet();
        AnimatorSet animSetXYb = new AnimatorSet();

        float imgv1x = imgv1.getX();
        float imgv1y = imgv1.getY();

        float imgv2x = imgv2.getX();
        float imgv2y = imgv2.getY();

        float imgv3x = imgv3.getX();
        float imgv3y = imgv3.getY();

        float imgv4x = imgv4.getX();
        float imgv4y = imgv4.getY();

        ObjectAnimator imganim1a = ObjectAnimator.ofFloat(imgv1,
                "y", imgv1.getY(), imgv3y);

        ObjectAnimator imganim1b = ObjectAnimator.ofFloat(imgv1,
                "x", imgv1.getX(), imgv3x);

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

        if(centerTile == 4 || centerTile == 1) {
            animSetXYa.playTogether(imganim1a, imganim2b, imganim3b, imganim4a);
            animSetXYa.setInterpolator(new AccelerateInterpolator());
            animSetXYb.playTogether(imganim1b, imganim2a, imganim3a, imganim4b);
            animSetXYb.setInterpolator(new DecelerateInterpolator());
            animSetXY.playTogether(animSetXYa, animSetXYb);
            animSetXY.setDuration(500);
            animSetXY.start();
        }
        else{
            animSetXYa.playTogether(imganim2a, imganim4b, imganim1b, imganim3a);
            animSetXYa.setInterpolator(new AccelerateInterpolator());
            animSetXYb.playTogether(imganim2b, imganim4a, imganim1a, imganim3b);
            animSetXYb.setInterpolator(new DecelerateInterpolator());
            animSetXY.playTogether(animSetXYa, animSetXYb);
            animSetXY.setDuration(500);
            animSetXY.start();
        }
    }

    private void animateDiagonalPan2() {
        AnimatorSet animSetXY = new AnimatorSet();
        AnimatorSet animSetXYa = new AnimatorSet();
        AnimatorSet animSetXYb = new AnimatorSet();

        float imgv1x = imgv1.getX();
        float imgv1y = imgv1.getY();

        float imgv2x = imgv2.getX();
        float imgv2y = imgv2.getY();

        float imgv3x = imgv3.getX();
        float imgv3y = imgv3.getY();

        float imgv4x = imgv4.getX();
        float imgv4y = imgv4.getY();

        ObjectAnimator imganim1a = ObjectAnimator.ofFloat(imgv1,
                "y", imgv1.getY(), imgv2y);

        ObjectAnimator imganim1b = ObjectAnimator.ofFloat(imgv1,
                "x", imgv1.getX(), imgv2x);

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

        if(centerTile == 4 || centerTile == 1) {
            animSetXYa.playTogether(imganim1a, imganim2b, imganim3b, imganim4a);
            animSetXYa.setInterpolator(new AccelerateInterpolator());
            animSetXYb.playTogether(imganim1b, imganim2a, imganim3a, imganim4b);
            animSetXYb.setInterpolator(new DecelerateInterpolator());
            animSetXY.playTogether(animSetXYa, animSetXYb);
            animSetXY.setDuration(500);
            animSetXY.start();
        }
        else{
            animSetXYa.playTogether(imganim2a, imganim4b, imganim1b, imganim3a);
            animSetXYa.setInterpolator(new AccelerateInterpolator());
            animSetXYb.playTogether(imganim2b, imganim4a, imganim1a, imganim3b);
            animSetXYb.setInterpolator(new DecelerateInterpolator());
            animSetXY.playTogether(animSetXYa, animSetXYb);
            animSetXY.setDuration(500);
            animSetXY.start();
        }
    }

    private int evaluateCenterRight(int centerTile){
        //clockwise
        //integer corresponds to the respective imageview
        switch (centerTile){
            case 1:
                runLayout.setVisibility(View.GONE);
                centerTile = 2;
                distanceLayout.setVisibility(View.VISIBLE);
                imgv4.bringToFront();
                imgv1.bringToFront();
                imgv2.bringToFront();
                break;
            case 2:
                distanceLayout.setVisibility(View.GONE);
                centerTile = 4;
                timeLayout.setVisibility(View.VISIBLE);
                imgv3.bringToFront();
                imgv2.bringToFront();
                imgv4.bringToFront();
                break;
            case 3:
                cardioLayout.setVisibility(View.GONE);
                centerTile = 1;
                runLayout.setVisibility(View.VISIBLE);
                imgv2.bringToFront();
                imgv3.bringToFront();
                imgv1.bringToFront();
                break;
            case 4:
                timeLayout.setVisibility(View.GONE);
                centerTile = 3;
                cardioLayout.setVisibility(View.VISIBLE);
                imgv1.bringToFront();
                imgv4.bringToFront();
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
                runLayout.setVisibility(View.GONE);
                centerTile = 3;
                cardioLayout.setVisibility(View.VISIBLE);
                imgv4.bringToFront();
                imgv1.bringToFront();
                imgv3.bringToFront();
                break;
            case 2:
                distanceLayout.setVisibility(View.GONE);
                centerTile = 1;
                runLayout.setVisibility(View.VISIBLE);
                imgv3.bringToFront();
                imgv2.bringToFront();
                imgv1.bringToFront();
                break;
            case 3:
                cardioLayout.setVisibility(View.GONE);
                centerTile = 4;
                timeLayout.setVisibility(View.VISIBLE);
                imgv2.bringToFront();
                imgv3.bringToFront();
                imgv4.bringToFront();
                break;
            case 4:
                timeLayout.setVisibility(View.GONE);
                centerTile = 2;
                distanceLayout.setVisibility(View.VISIBLE);
                imgv1.bringToFront();
                imgv4.bringToFront();
                imgv2.bringToFront();
                break;
        }
        return centerTile;
    }

    @Override
    public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker view, int hourOfDay, int minute, int seconds) {

    }

    @Override
    public void onClick(View v) {

        if(v == beginbtn){
            switch (centerTile){
                case 1:
                    Toast.makeText(getContext(), "Beginning Just Run...", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(getContext(), "Beginning Distance...", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getContext(), "Beginning Cardio...", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(getContext(), "Beginning Time...", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        if (v == connectbtn) {
            Toast.makeText(getContext(), "Connecting...", Toast.LENGTH_SHORT).show();
        }

        //Cardio Panel Items

        if(v == cardiopanelType){
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Cardio Type :");

            builderSingle.setSingleChoiceItems(cardioPanelTypeArray, indexCardioPanelType, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    indexCardioPanelType = which;
                    cardioPanelTypeValue = cardioPanelTypeArray[which];
                    String strName = cardioPanelTypeArray[which];
                    cardioPanelTypetv.setText(strName+" >");
                    dialog.dismiss();
                }
            });

            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.show();
        }
        if(v == cardiopanelFeedback){
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Feedback :");

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
            arrayAdapter.add("None");

            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builderSingle.show();
        }
        if(v == cardiopanelLocation){
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Location :");

            builderSingle.setSingleChoiceItems(cardioPanelLocationArray, indexCardioPanelLocation, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    indexCardioPanelLocation = which;
                    cardioPanelLocationValue = cardioPanelLocationArray[which];
                    String strName = cardioPanelLocationArray[which];
                    cardioPanelLocationtv.setText(strName+" >");
                    dialog.dismiss();
                }
            });

            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.show();
        }

        //Time Panel Items

        if(v == timepanelTime){
            try {
                Date date = sdf.parse(timePanelTimeValue);
                dateTime.setTime(date);
            }
            catch(ParseException e){
                System.out.println(e);
            }
            MyTimePickerDialog myTimePickerDialog = new MyTimePickerDialog(getContext(), new MyTimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker view, int hourOfDay, int minute, int seconds) {
                    timePanelTimeValue = (String.format("%02d", hourOfDay)+
                            ":" + String.format("%02d", minute) +
                            ":" + String.format("%02d", seconds));
                    try {
                        timePanelTimetv.setText(timePanelTimeValue+" >");
                        Date date = sdf.parse(timePanelTimeValue);
                        dateTime.setTime(date);
                    }
                    catch (ParseException e){
                        System.out.println(e);
                    }
                }
            }, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), dateTime.get(Calendar. SECOND), true);
            myTimePickerDialog.show();
        }
        if(v == timepanelLocation){
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Location :");

            builderSingle.setSingleChoiceItems(timePanelLocationArray, indexTimePanelLocation, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    indexTimePanelLocation = which;
                    timePanelLocationValue = timePanelLocationArray[which];
                    String strName = timePanelLocationArray[which];
                    timePanelLocationtv.setText(strName+" >");
                    dialog.dismiss();
                }
            });

            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.show();
        }
        if(v == timepanelFeedback){
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Feedback :");

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
            arrayAdapter.add("None");

            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builderSingle.show();
        }

        //Distance Panel

        if(v == distancepanelDistance){
            RelativeLayout linearLayout = new RelativeLayout(getContext());
            final NumberPicker aNumberPicker = new NumberPicker(getContext());
            aNumberPicker.setMaxValue(30);
            aNumberPicker.setMinValue(2);
            if(distancePanelDistanceValue!=null)
                aNumberPicker.setValue(Integer.parseInt(distancePanelDistanceValue));

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
            RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

            linearLayout.setLayoutParams(params);
            linearLayout.addView(aNumberPicker,numPicerParams);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setTitle("Distance :");
            alertDialogBuilder.setView(linearLayout);
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    distancePanelDistanceValue = aNumberPicker.getValue()+"";
                                    distancePanelDistancetv.setText(aNumberPicker.getValue()+"km >");

                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        if(v == distancepanelFeedback){
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Feedback :");

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
            arrayAdapter.add("None");

            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builderSingle.show();
        }
        if(v == distancepanelLocation){
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Location :");

            builderSingle.setSingleChoiceItems(distancePanelLocationArray, indexDistancePanelLocation, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    indexDistancePanelLocation = which;
                    distancePanelLocationValue = distancePanelLocationArray[which];
                    String strName = distancePanelLocationArray[which];
                    distancePanelLocationtv.setText(strName+" >");
                    dialog.dismiss();
                }
            });

            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builderSingle.show();
        }
        if(v == distancepanelTime){
            try {
                Date date = sdf.parse(distancePanelTimeValue);
                dateTime.setTime(date);
            }
            catch(ParseException e){
                System.out.println(e);
            }
            MyTimePickerDialog myTimePickerDialog = new MyTimePickerDialog(getContext(), new MyTimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker view, int hourOfDay, int minute, int seconds) {
                    distancePanelTimeValue = (String.format("%02d", hourOfDay)+
                            ":" + String.format("%02d", minute) +
                            ":" + String.format("%02d", seconds));
                    try {
                        distancePanelTimetv.setText(distancePanelTimeValue+" >");
                        Date date = sdf.parse(distancePanelTimeValue);
                        dateTime.setTime(date);
                    }
                    catch (ParseException e){
                        System.out.println(e);
                    }
                }
            }, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), dateTime.get(Calendar. SECOND), true);
            myTimePickerDialog.show();
        }

        //Run Panel

        if(v == runpanelDistance){
            RelativeLayout linearLayout = new RelativeLayout(getContext());
            final NumberPicker aNumberPicker = new NumberPicker(getContext());
            aNumberPicker.setMaxValue(30);
            aNumberPicker.setMinValue(2);
            if(runPanelDistanceValue!=null)
                aNumberPicker.setValue(Integer.parseInt(runPanelDistanceValue));

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
            RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

            linearLayout.setLayoutParams(params);
            linearLayout.addView(aNumberPicker,numPicerParams);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setTitle("Distance :");
            alertDialogBuilder.setView(linearLayout);
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    runPanelDistanceValue = aNumberPicker.getValue()+"";
                                    runPanelDistancetv.setText(aNumberPicker.getValue()+"km >");

                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        if(v == runpanelFeedback){
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Feedback :");

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
            arrayAdapter.add("None");

            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builderSingle.show();
        }
    }
}
