package koolkat.faberlabstestapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ikovac.timepickerwithseconds.MyTimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Admin on 4/29/2017.
 */

public class TilesFragment extends android.support.v4.app.Fragment implements View.OnClickListener, MyTimePickerDialog.OnTimeSetListener {

    Button tilesConnectButton, tilesBeginButton;
    ImageView runTileImageView, distanceTileImageView, cardioTileImageView, timeTileImageView;
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
    private int indexCardioPanelType = 0;

    private String[] cardioPanelLocationArray = {"Indoor", "Outdoor"};
    private int indexCardioPanelLocation = 0;

    private String[] cardioPanelFeedbackArray = {"None"};
    private int indexCardioPanelFeedback = 0;

    private String[] timePanelLocationArray = {"Indoor", "Outdoor"};
    private int indexTimePanelLocation = 1;

    private String[] timePanelFeedbackArray = {"None"};
    private int indexTimePanelFeedback = 0;

    private String[] distancePanelLocationArray = {"Indoor", "Outdoor"};
    private int indexDistancePanelLocation = 1;

    private String[] distancePanelFeedbackArray = {"None"};
    private int indexDistancePanelFeedback = 0;

    private String[] runPanelFeedbackArray = {"None"};
    private int indexRunPanelFeedback = 0;

    static String cardioPanelTypeValue = "Endurance", cardioPanelLocationValue = "Indoor";
    static String timePanelTimeValue = "00:30:12", timePanelLocationValue = "Outdoor";
    static String distancePanelDistanceValue = "18", distancePanelLocationValue = "Outdoor", distancePanelTimeValue = "00:14:00";
    static String runPanelDistanceValue = "18";


    public static TilesFragment newInstance() {
        return new TilesFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        centerTile = 4;
        View view = inflater.inflate(R.layout.fragment_tiles, container, false);

        dateTime = Calendar.getInstance();
        sdf = new SimpleDateFormat("HH:mm:ss");

        //For when user swipes anywhere in the tiles layout
        tilesRelativeLayout = (RelativeLayout) view.findViewById(R.id.tiles_relative_layout);
        tilesRelativeLayout.setOnTouchListener(new GestureHelper(getContext()) {

            @Override
            public void onSwipeLeft() {
                animateRight();
                centerTile = evaluateCenterRight(centerTile);
            }

            @Override
            public void onSwipeRight() {
                animateLeft();
                centerTile = evaluateCenterLeft(centerTile);
            }
        });

        tilesLayout = (LinearLayout) view.findViewById(R.id.tiles_layout);

        runLayout = (LinearLayout) view.findViewById(R.id.run_panel);
        cardioLayout = (LinearLayout) view.findViewById(R.id.cardio_panel);
        timeLayout = (LinearLayout) view.findViewById(R.id.time_panel);
        distanceLayout = (LinearLayout) view.findViewById(R.id.distance_panel);

        //cardio panel views
        cardiopanelType = (RelativeLayout) view.findViewById(R.id.cardiopanel_type);
        cardiopanelType.setOnClickListener(this);
        cardioPanelTypetv = (TextView) view.findViewById(R.id.cardiopanel_typetv);
        cardiopanelFeedback = (RelativeLayout) view.findViewById(R.id.cardiopanel_feedback);
        cardiopanelFeedback.setOnClickListener(this);
        cardiopanelLocation = (RelativeLayout) view.findViewById(R.id.cardiopanel_location);
        cardiopanelLocation.setOnClickListener(this);
        cardioPanelLocationtv = (TextView) view.findViewById(R.id.cardiopanel_locationtv);

        //time panel views
        timepanelTime = (RelativeLayout) view.findViewById(R.id.timepanel_time);
        timepanelTime.setOnClickListener(this);
        timePanelTimetv = (TextView) view.findViewById(R.id.timepanel_timetv);
        timepanelFeedback = (RelativeLayout) view.findViewById(R.id.timepanel_feedback);
        timepanelFeedback.setOnClickListener(this);
        timepanelLocation = (RelativeLayout) view.findViewById(R.id.timepanel_location);
        timepanelLocation.setOnClickListener(this);
        timePanelLocationtv = (TextView) view.findViewById(R.id.timepanel_locationtv);

        //distance panel views
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

        //run panel views
        runpanelDistance = (RelativeLayout) view.findViewById(R.id.runpanel_distance);
        runpanelDistance.setOnClickListener(this);
        runPanelDistancetv = (TextView) view.findViewById(R.id.runpanel_distancetv);
        runpanelFeedback = (RelativeLayout) view.findViewById(R.id.runpanel_feedback);
        runpanelFeedback.setOnClickListener(this);
        runPanelFeedbacktv = (TextView) view.findViewById(R.id.runpanel_feedbacktv);

        tilesConnectButton = (Button) view.findViewById(R.id.tiles_connect_button);
        tilesBeginButton = (Button) view.findViewById(R.id.tiles_begin_button);
        tilesBeginButton.setOnClickListener(this);
        //first image
        runTileImageView = (ImageView) view.findViewById(R.id.run_tile_imageview);
        runTileImageView.setTag(R.drawable.runbox);
        //second image
        distanceTileImageView = (ImageView) view.findViewById(R.id.distance_tile_imageview);
        distanceTileImageView.setTag(R.drawable.distancebox);
        distanceTileImageView.bringToFront();
        //third image
        cardioTileImageView = (ImageView) view.findViewById(R.id.cardio_tile_imageview);
        cardioTileImageView.setTag(R.drawable.cardiobox);
        cardioTileImageView.bringToFront();
        //fourth image
        timeTileImageView = (ImageView) view.findViewById(R.id.time_tile_imageview);
        timeTileImageView.setTag(R.drawable.timebox);
        timeTileImageView.bringToFront();
        tilesConnectButton.setOnClickListener(this);

        //For when user swipes on top of the tiles
        runTileImageView.setOnTouchListener(new GestureHelper(getContext()) {
            @Override
            public void onSwipeLeft() {
                animateRight();
                centerTile = evaluateCenterRight(centerTile);
            }

            @Override
            public void onSwipeRight() {
                animateLeft();
                centerTile = evaluateCenterLeft(centerTile);
            }

            @Override
            public void onClick() {
                if (centerTile != 1) {
                    if (centerTile == 2) {
                        animateLeft();
                        centerTile = evaluateCenterLeft(centerTile);
                    } else if (centerTile == 3) {
                        animateRight();
                        centerTile = evaluateCenterRight(centerTile);
                    }
                }
            }
        });

        distanceTileImageView.setOnTouchListener(new GestureHelper(getContext()) {

            @Override
            public void onSwipeLeft() {
                animateRight();
                centerTile = evaluateCenterRight(centerTile);
            }

            @Override
            public void onSwipeRight() {
                animateLeft();
                centerTile = evaluateCenterLeft(centerTile);
            }

            @Override
            public void onClick() {
                if (centerTile != 2) {
                    if (centerTile == 1) {
                        animateRight();
                        centerTile = evaluateCenterRight(centerTile);
                    } else if (centerTile == 4) {
                        animateLeft();
                        centerTile = evaluateCenterLeft(centerTile);
                    }
                }
            }
        });

        cardioTileImageView.setOnTouchListener(new GestureHelper(getContext()) {

            @Override
            public void onSwipeLeft() {
                animateRight();
                centerTile = evaluateCenterRight(centerTile);
            }

            @Override
            public void onSwipeRight() {
                animateLeft();
                centerTile = evaluateCenterLeft(centerTile);
            }

            @Override
            public void onClick() {
                if (centerTile != 3) {
                    if (centerTile == 4) {
                        animateRight();
                        centerTile = evaluateCenterRight(centerTile);
                    } else if (centerTile == 1) {
                        animateLeft();
                        centerTile = evaluateCenterLeft(centerTile);
                    }
                }
            }
        });

        timeTileImageView.setOnTouchListener(new GestureHelper(getContext()) {

            @Override
            public void onSwipeLeft() {
                animateRight();
                centerTile = evaluateCenterRight(centerTile);
            }

            @Override
            public void onSwipeRight() {
                animateLeft();
                centerTile = evaluateCenterLeft(centerTile);
            }

            @Override
            public void onClick() {
                if (centerTile != 4) {
                    if (centerTile == 3) {
                        animateLeft();
                        centerTile = evaluateCenterLeft(centerTile);
                    } else if (centerTile == 2) {
                        animateRight();
                        centerTile = evaluateCenterRight(centerTile);
                    }
                }
            }
        });

        return view;
    }

    //For Clock-wise Animation
    private void animateRight() {

        AnimatorSet animSetXY = new AnimatorSet();
        AnimatorSet animSetXYa = new AnimatorSet();
        AnimatorSet animSetXYb = new AnimatorSet();

        float imgv1x = runTileImageView.getX();
        float imgv1y = runTileImageView.getY();

        float imgv2x = distanceTileImageView.getX();
        float imgv2y = distanceTileImageView.getY();

        float imgv3x = cardioTileImageView.getX();
        float imgv3y = cardioTileImageView.getY();

        float imgv4x = timeTileImageView.getX();
        float imgv4y = timeTileImageView.getY();

        ObjectAnimator imganim1a = ObjectAnimator.ofFloat(runTileImageView,
                "y", runTileImageView.getY(), imgv3y);

        ObjectAnimator imganim1b = ObjectAnimator.ofFloat(runTileImageView,
                "x", runTileImageView.getX(), imgv3x);

        ObjectAnimator imganim2a = ObjectAnimator.ofFloat(distanceTileImageView,
                "y", distanceTileImageView.getY(), imgv1y);

        ObjectAnimator imganim2b = ObjectAnimator.ofFloat(distanceTileImageView,
                "x", distanceTileImageView.getX(), imgv1x);

        ObjectAnimator imganim3a = ObjectAnimator.ofFloat(cardioTileImageView,
                "y", cardioTileImageView.getY(), imgv4y);

        ObjectAnimator imganim3b = ObjectAnimator.ofFloat(cardioTileImageView,
                "x", cardioTileImageView.getX(), imgv4x);

        ObjectAnimator imganim4a = ObjectAnimator.ofFloat(timeTileImageView,
                "y", timeTileImageView.getY(), imgv2y);

        ObjectAnimator imganim4b = ObjectAnimator.ofFloat(timeTileImageView,
                "x", timeTileImageView.getX(), imgv2x);

        if (centerTile == 4 || centerTile == 1) {
            animSetXYa.playTogether(imganim1a, imganim2b, imganim3b, imganim4a);
            animSetXYa.setInterpolator(new AccelerateInterpolator());
            animSetXYb.playTogether(imganim1b, imganim2a, imganim3a, imganim4b);
            animSetXYb.setInterpolator(new DecelerateInterpolator());
            animSetXY.playTogether(animSetXYa, animSetXYb);
            animSetXY.setDuration(500);
            animSetXY.start();
        } else {
            animSetXYa.playTogether(imganim2a, imganim4b, imganim1b, imganim3a);
            animSetXYa.setInterpolator(new AccelerateInterpolator());
            animSetXYb.playTogether(imganim2b, imganim4a, imganim1a, imganim3b);
            animSetXYb.setInterpolator(new DecelerateInterpolator());
            animSetXY.playTogether(animSetXYa, animSetXYb);
            animSetXY.setDuration(500);
            animSetXY.start();
        }
    }

    //For counter-clockwise animation
    private void animateLeft() {
        AnimatorSet animSetXY = new AnimatorSet();
        AnimatorSet animSetXYa = new AnimatorSet();
        AnimatorSet animSetXYb = new AnimatorSet();

        float imgv1x = runTileImageView.getX();
        float imgv1y = runTileImageView.getY();

        float imgv2x = distanceTileImageView.getX();
        float imgv2y = distanceTileImageView.getY();

        float imgv3x = cardioTileImageView.getX();
        float imgv3y = cardioTileImageView.getY();

        float imgv4x = timeTileImageView.getX();
        float imgv4y = timeTileImageView.getY();

        ObjectAnimator imganim1a = ObjectAnimator.ofFloat(runTileImageView,
                "y", runTileImageView.getY(), imgv2y);

        ObjectAnimator imganim1b = ObjectAnimator.ofFloat(runTileImageView,
                "x", runTileImageView.getX(), imgv2x);

        ObjectAnimator imganim2a = ObjectAnimator.ofFloat(distanceTileImageView,
                "y", distanceTileImageView.getY(), imgv4y);

        ObjectAnimator imganim2b = ObjectAnimator.ofFloat(distanceTileImageView,
                "x", distanceTileImageView.getX(), imgv4x);

        ObjectAnimator imganim3a = ObjectAnimator.ofFloat(cardioTileImageView,
                "y", cardioTileImageView.getY(), imgv1y);

        ObjectAnimator imganim3b = ObjectAnimator.ofFloat(cardioTileImageView,
                "x", cardioTileImageView.getX(), imgv1x);

        ObjectAnimator imganim4a = ObjectAnimator.ofFloat(timeTileImageView,
                "y", timeTileImageView.getY(), imgv3y);

        ObjectAnimator imganim4b = ObjectAnimator.ofFloat(timeTileImageView,
                "x", timeTileImageView.getX(), imgv3x);

        if (centerTile == 4 || centerTile == 1) {
            animSetXYa.playTogether(imganim1a, imganim2b, imganim3b, imganim4a);
            animSetXYa.setInterpolator(new AccelerateInterpolator());
            animSetXYb.playTogether(imganim1b, imganim2a, imganim3a, imganim4b);
            animSetXYb.setInterpolator(new DecelerateInterpolator());
            animSetXY.playTogether(animSetXYa, animSetXYb);
            animSetXY.setDuration(500);
            animSetXY.start();
        } else {
            animSetXYa.playTogether(imganim2a, imganim4b, imganim1b, imganim3a);
            animSetXYa.setInterpolator(new AccelerateInterpolator());
            animSetXYb.playTogether(imganim2b, imganim4a, imganim1a, imganim3b);
            animSetXYb.setInterpolator(new DecelerateInterpolator());
            animSetXY.playTogether(animSetXYa, animSetXYb);
            animSetXY.setDuration(500);
            animSetXY.start();
        }
    }

    //Evaluates and returns the center after a clockwise animation
    private int evaluateCenterRight(int centerTile) {
        //clockwise
        //integer corresponds to the respective imageview
        switch (centerTile) {
            case 1:
                runLayout.setVisibility(View.GONE);
                centerTile = 2;
                distanceLayout.setVisibility(View.VISIBLE);
                timeTileImageView.bringToFront();
                runTileImageView.bringToFront();
                distanceTileImageView.bringToFront();
                break;
            case 2:
                distanceLayout.setVisibility(View.GONE);
                centerTile = 4;
                timeLayout.setVisibility(View.VISIBLE);
                cardioTileImageView.bringToFront();
                distanceTileImageView.bringToFront();
                timeTileImageView.bringToFront();
                break;
            case 3:
                cardioLayout.setVisibility(View.GONE);
                centerTile = 1;
                runLayout.setVisibility(View.VISIBLE);
                distanceTileImageView.bringToFront();
                cardioTileImageView.bringToFront();
                runTileImageView.bringToFront();
                break;
            case 4:
                timeLayout.setVisibility(View.GONE);
                centerTile = 3;
                cardioLayout.setVisibility(View.VISIBLE);
                runTileImageView.bringToFront();
                timeTileImageView.bringToFront();
                cardioTileImageView.bringToFront();
                break;
        }
        return centerTile;
    }

    //Evaluates and returns center after counter-clockwise animation
    private int evaluateCenterLeft(int centerTile) {
        //anti-clockwise
        //integer corresponds to the respective imageview
        switch (centerTile) {
            case 1:
                runLayout.setVisibility(View.GONE);
                centerTile = 3;
                cardioLayout.setVisibility(View.VISIBLE);
                timeTileImageView.bringToFront();
                runTileImageView.bringToFront();
                cardioTileImageView.bringToFront();
                break;
            case 2:
                distanceLayout.setVisibility(View.GONE);
                centerTile = 1;
                runLayout.setVisibility(View.VISIBLE);
                cardioTileImageView.bringToFront();
                distanceTileImageView.bringToFront();
                runTileImageView.bringToFront();
                break;
            case 3:
                cardioLayout.setVisibility(View.GONE);
                centerTile = 4;
                timeLayout.setVisibility(View.VISIBLE);
                distanceTileImageView.bringToFront();
                cardioTileImageView.bringToFront();
                timeTileImageView.bringToFront();
                break;
            case 4:
                timeLayout.setVisibility(View.GONE);
                centerTile = 2;
                distanceLayout.setVisibility(View.VISIBLE);
                runTileImageView.bringToFront();
                timeTileImageView.bringToFront();
                distanceTileImageView.bringToFront();
                break;
        }
        return centerTile;
    }

    @Override
    public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker view, int hourOfDay, int minute, int seconds) {

    }

    @Override
    public void onClick(View v) {

        if (v == tilesBeginButton) {
            android.support.v4.app.Fragment childFragment = new CountdownFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.tiles_framelayout, childFragment).commit();
        }

        if (v == tilesConnectButton) {
            Toast.makeText(getContext(), "Connecting...", Toast.LENGTH_SHORT).show();
        }

        //Cardio Panel Items

        if (v == cardiopanelType) {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Cardio Type :");

            builderSingle.setSingleChoiceItems(cardioPanelTypeArray, indexCardioPanelType, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    indexCardioPanelType = which;
                    cardioPanelTypeValue = cardioPanelTypeArray[which];
                    String strName = cardioPanelTypeArray[which];
                    cardioPanelTypetv.setText(strName + " >");
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
        if (v == cardiopanelFeedback) {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Feedback :");

            builderSingle.setSingleChoiceItems(cardioPanelFeedbackArray, indexCardioPanelFeedback, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    indexCardioPanelFeedback = which;
                    //cardioPanelLocationValue = cardioPanelLocationArray[which];
                    //String strName = cardioPanelLocationArray[which];
                    //cardioPanelLocationtv.setText(strName+" >");
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
        if (v == cardiopanelLocation) {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Location :");

            builderSingle.setSingleChoiceItems(cardioPanelLocationArray, indexCardioPanelLocation, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    indexCardioPanelLocation = which;
                    cardioPanelLocationValue = cardioPanelLocationArray[which];
                    String strName = cardioPanelLocationArray[which];
                    cardioPanelLocationtv.setText(strName + " >");
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

        if (v == timepanelTime) {
            try {
                Date date = sdf.parse(timePanelTimeValue);
                dateTime.setTime(date);
            } catch (ParseException e) {
                System.out.println(e);
            }
            MyTimePickerDialog myTimePickerDialog = new MyTimePickerDialog(getContext(), new MyTimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker view, int hourOfDay, int minute, int seconds) {
                    timePanelTimeValue = (String.format("%02d", hourOfDay) +
                            ":" + String.format("%02d", minute) +
                            ":" + String.format("%02d", seconds));
                    try {
                        timePanelTimetv.setText(timePanelTimeValue + " >");
                        Date date = sdf.parse(timePanelTimeValue);
                        dateTime.setTime(date);
                    } catch (ParseException e) {
                        System.out.println(e);
                    }
                }
            }, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), dateTime.get(Calendar.SECOND), true);
            myTimePickerDialog.show();
        }
        if (v == timepanelLocation) {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Location :");

            builderSingle.setSingleChoiceItems(timePanelLocationArray, indexTimePanelLocation, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    indexTimePanelLocation = which;
                    timePanelLocationValue = timePanelLocationArray[which];
                    String strName = timePanelLocationArray[which];
                    timePanelLocationtv.setText(strName + " >");
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
        if (v == timepanelFeedback) {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Feedback :");

            builderSingle.setSingleChoiceItems(timePanelFeedbackArray, indexTimePanelFeedback, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    indexTimePanelFeedback = which;
                    //cardioPanelLocationValue = cardioPanelLocationArray[which];
                    //String strName = cardioPanelLocationArray[which];
                    //cardioPanelLocationtv.setText(strName+" >");
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

        //Distance Panel

        if (v == distancepanelDistance) {
            RelativeLayout linearLayout = new RelativeLayout(getContext());
            final NumberPicker aNumberPicker = new NumberPicker(getContext());
            aNumberPicker.setMaxValue(30);
            aNumberPicker.setMinValue(2);
            if (distancePanelDistanceValue != null)
                aNumberPicker.setValue(Integer.parseInt(distancePanelDistanceValue));

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
            RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

            linearLayout.setLayoutParams(params);
            linearLayout.addView(aNumberPicker, numPicerParams);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setTitle("Distance :");
            alertDialogBuilder.setView(linearLayout);
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    distancePanelDistanceValue = aNumberPicker.getValue() + "";
                                    distancePanelDistancetv.setText(aNumberPicker.getValue() + "km >");

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
        if (v == distancepanelFeedback) {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Feedback :");

            builderSingle.setSingleChoiceItems(distancePanelFeedbackArray, indexDistancePanelFeedback, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    indexDistancePanelFeedback = which;
                    //cardioPanelLocationValue = cardioPanelLocationArray[which];
                    //String strName = cardioPanelLocationArray[which];
                    //cardioPanelLocationtv.setText(strName+" >");
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
        if (v == distancepanelLocation) {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Location :");

            builderSingle.setSingleChoiceItems(distancePanelLocationArray, indexDistancePanelLocation, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    indexDistancePanelLocation = which;
                    distancePanelLocationValue = distancePanelLocationArray[which];
                    String strName = distancePanelLocationArray[which];
                    distancePanelLocationtv.setText(strName + " >");
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
        if (v == distancepanelTime) {
            try {
                Date date = sdf.parse(distancePanelTimeValue);
                dateTime.setTime(date);
            } catch (ParseException e) {
                System.out.println(e);
            }
            MyTimePickerDialog myTimePickerDialog = new MyTimePickerDialog(getContext(), new MyTimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(com.ikovac.timepickerwithseconds.TimePicker view, int hourOfDay, int minute, int seconds) {
                    distancePanelTimeValue = (String.format("%02d", hourOfDay) +
                            ":" + String.format("%02d", minute) +
                            ":" + String.format("%02d", seconds));
                    try {
                        distancePanelTimetv.setText(distancePanelTimeValue + " >");
                        Date date = sdf.parse(distancePanelTimeValue);
                        dateTime.setTime(date);
                    } catch (ParseException e) {
                        System.out.println(e);
                    }
                }
            }, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), dateTime.get(Calendar.SECOND), true);
            myTimePickerDialog.show();
        }

        //Run Panel

        if (v == runpanelDistance) {
            RelativeLayout linearLayout = new RelativeLayout(getContext());
            final NumberPicker aNumberPicker = new NumberPicker(getContext());
            aNumberPicker.setMaxValue(30);
            aNumberPicker.setMinValue(2);
            if (runPanelDistanceValue != null)
                aNumberPicker.setValue(Integer.parseInt(runPanelDistanceValue));

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
            RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

            linearLayout.setLayoutParams(params);
            linearLayout.addView(aNumberPicker, numPicerParams);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
            alertDialogBuilder.setTitle("Distance :");
            alertDialogBuilder.setView(linearLayout);
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    runPanelDistanceValue = aNumberPicker.getValue() + "";
                                    runPanelDistancetv.setText(aNumberPicker.getValue() + "km >");

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

        if (v == runpanelFeedback) {
            AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
            builderSingle.setIcon(R.mipmap.ic_launcher);
            builderSingle.setTitle("Feedback :");

            builderSingle.setSingleChoiceItems(runPanelFeedbackArray, indexRunPanelFeedback, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    indexRunPanelFeedback = which;
                    //cardioPanelLocationValue = cardioPanelLocationArray[which];
                    //String strName = cardioPanelLocationArray[which];
                    //cardioPanelLocationtv.setText(strName+" >");
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
    }
}
