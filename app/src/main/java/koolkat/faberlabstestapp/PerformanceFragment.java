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
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
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
    LinearLayout runLayout, cardioLayout, timeLayout, distanceLayout;
    RelativeLayout cardiopanelType, cardiopanelFeedback, cardiopanelLocation;
    RelativeLayout timepanelTime, timepanelFeedback, timepanelLocation;
    RelativeLayout distancepanelDistance, distancepanelFeedback, distancepanelLocation, distancepanelTime;
    RelativeLayout runpanelDistance, runpanelFeedback;
    Button cardioBeginButton, timeBeginButton, distanceBeginButton, runBeginButton;
    TextView cardioPanelTypetv, cardioPanelLocationtv;
    TextView timePanelTimetv, timePanelLocationtv;
    TextView distancePanelDistancetv, distancePanelLocationtv, distancePanelTimetv;
    TextView runPanelDistancetv, runPanelFeedbacktv;
    Calendar dateTime;
    SimpleDateFormat sdf;

    String cardioPanelTypeValue, cardioPanelLocationValue;
    String timePanelTimeValue="00:30:12", timePanelLocationValue;
    String distancePanelDistanceValue="18", distancePanelLocationValue, distancePanelTimeValue="00:14:00";
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

        runLayout = (LinearLayout) view.findViewById(R.id.run_panel);
        cardioLayout = (LinearLayout) view.findViewById(R.id.cardio_panel);
        timeLayout = (LinearLayout) view.findViewById(R.id.time_panel);
        distanceLayout = (LinearLayout) view.findViewById(R.id.distance_panel);

        cardioBeginButton = (Button) view.findViewById(R.id.cardio_beginbtn);
        cardioBeginButton.setOnClickListener(this);
        timeBeginButton = (Button) view.findViewById(R.id.time_beginbtn);
        timeBeginButton.setOnClickListener(this);
        distanceBeginButton = (Button) view.findViewById(R.id.distance_beginbtn);
        distanceBeginButton.setOnClickListener(this);
        runBeginButton = (Button) view.findViewById(R.id.run_beginbtn);
        runBeginButton.setOnClickListener(this);


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
        imgv1.setOnClickListener(this);
        imgv2.setOnClickListener(this);
        imgv3.setOnClickListener(this);
        imgv4.setOnClickListener(this);
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

        animSetXYa.playTogether(imganim1b, imganim2b, imganim3b, imganim4b);
        animSetXYa.setInterpolator(new DecelerateInterpolator());
        animSetXYb.playTogether(imganim1a, imganim2a, imganim3a, imganim4a);
        animSetXYb.setInterpolator(new AccelerateDecelerateInterpolator());
        animSetXY.playTogether(animSetXYa, animSetXYb);
        animSetXY.setDuration(300);
        animSetXY.start();
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

        animSetXYa.playTogether(imganim1b, imganim2b, imganim3b, imganim4b);
        animSetXYa.setInterpolator(new DecelerateInterpolator());
        animSetXYb.playTogether(imganim1a, imganim2a, imganim3a, imganim4a);
        animSetXYb.setInterpolator(new AccelerateDecelerateInterpolator());
        animSetXY.playTogether(animSetXYa, animSetXYb);
        animSetXY.setDuration(300);
        animSetXY.start();
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

        if (v == connectbtn) {
            Toast.makeText(getContext(), "Connecting...", Toast.LENGTH_SHORT).show();
        }
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

        //Cardio Panel Items

        if(v == cardiopanelType){
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Cardio Type :");

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
            arrayAdapter.add("Endurance");
            arrayAdapter.add("Strength");

            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    cardioPanelTypetv.setText(strName+" >");
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

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
            arrayAdapter.add("Indoor");
            arrayAdapter.add("Outdoor");

            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    cardioPanelLocationtv.setText(strName+" >");
                }
            });
            builderSingle.show();
        }
        if(v == cardioBeginButton){
            Toast.makeText(getContext(), "Beginning Cardio...", Toast.LENGTH_SHORT).show();
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

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
            arrayAdapter.add("Outdoor");
            arrayAdapter.add("Indoor");

            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    timePanelLocationtv.setText(strName+" >");
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
        if(v == timeBeginButton){
            Toast.makeText(getContext(), "Beginning Time...", Toast.LENGTH_SHORT).show();
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

            final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice);
            arrayAdapter.add("Outdoor");
            arrayAdapter.add("Indoor");

            builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String strName = arrayAdapter.getItem(which);
                    distancePanelLocationtv.setText(strName+" >");
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
        if(v == distanceBeginButton){
            Toast.makeText(getContext(), "Beginning Distance...", Toast.LENGTH_SHORT).show();
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
        if(v == runBeginButton){
            Toast.makeText(getContext(), "Beginning Just Run...", Toast.LENGTH_SHORT).show();
        }
    }
}
