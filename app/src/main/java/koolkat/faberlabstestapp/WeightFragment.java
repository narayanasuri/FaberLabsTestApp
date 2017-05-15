package koolkat.faberlabstestapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Admin on 4/29/2017.
 */

public class WeightFragment extends Fragment implements View.OnClickListener {

    Button startbtn, cancelbtn;
    TextView weightTimerText;
    ProgressBar weightTimerProgressBar;
    int n;
    CountDownTimer cdt;
    Boolean isTimerOn=false;
    Calendar dateTime;
    SimpleDateFormat sdf;

    public static WeightFragment newInstance() {
        return new WeightFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weight, container, false);
        startbtn = (Button) view.findViewById(R.id.startbtn);
        cancelbtn = (Button) view.findViewById(R.id.cancel_btn);
        startbtn.setOnClickListener(this);
        cancelbtn.setOnClickListener(this);
        weightTimerText = (TextView) view.findViewById(R.id.weight_tab_timertext);
        weightTimerProgressBar = (ProgressBar) view.findViewById(R.id.progressBar2);
        weightTimerProgressBar.setProgress(0);
        n = 10;
        isTimerOn = true;
        dateTime = Calendar.getInstance();
        sdf = new SimpleDateFormat("HH:mm:ss");

        //Countdown starts
        cdt = new CountDownTimer(12000, 1000) {
            public void onTick(long millisUntilFinished) {
                weightTimerText.setText(n+"");
                weightTimerProgressBar.setProgress((10-n)*10);
                n--;
            }
            public void onFinish() {
                //When the countdown timer finishes
                android.support.v4.app.Fragment childFragment = new TrainingFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.weight_tab, childFragment).commit();
            }
        }.start();
        try {
            Date date = sdf.parse(PerformanceFragment.timePanelTimeValue);
            dateTime.setTime(date);
        }
        catch(ParseException e){
            System.out.println(e);
        }
        long x = dateTime.get(Calendar.HOUR_OF_DAY)*60*60;
        long y = dateTime.get(Calendar.MINUTE)*60;
        long z = dateTime.get(Calendar.SECOND);
        long progressBarTime = (x+y+z) * 1000;
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==startbtn){
            android.support.v4.app.Fragment childFragment = new TrainingFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.weight_tab, childFragment).commit();
        }
        if(v==cancelbtn){
            android.support.v4.app.Fragment childFragment = new PerformanceFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.weight_tab, childFragment).commit();
        }
    }
}
