package koolkat.faberlabstestapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Admin on 4/29/2017.
 */

public class CountdownFragment extends Fragment implements View.OnClickListener {

    Button countDownStartButton, countDownCancelButton;
    TextView countDownTimerText;
    ProgressBar countDownProgressBar;
    int countDownTime;
    CountDownTimer cdt;
    Boolean isTimerOn = false;
    Calendar dateTime;
    SimpleDateFormat sdf;

    public static CountdownFragment newInstance() {
        return new CountdownFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_countdown, container, false);
        countDownStartButton = (Button) view.findViewById(R.id.countdown_start_button);
        countDownCancelButton = (Button) view.findViewById(R.id.countdown_cancel_button);
        countDownStartButton.setOnClickListener(this);
        countDownCancelButton.setOnClickListener(this);
        countDownTimerText = (TextView) view.findViewById(R.id.countdown_progressbar_text);
        countDownProgressBar = (ProgressBar) view.findViewById(R.id.countdown_progressbar);
        countDownProgressBar.setProgress(0);
        countDownTime = 10;
        isTimerOn = true;
        dateTime = Calendar.getInstance();
        sdf = new SimpleDateFormat("HH:mm:ss");

        //Countdown starts
        cdt = new CountDownTimer(12000, 1000) {
            public void onTick(long millisUntilFinished) {
                countDownTimerText.setText(countDownTime + "");
                countDownProgressBar.setProgress((10 - countDownTime) * 10);
                countDownTime--;
            }

            public void onFinish() {
                //When the countdown timer finishes
                android.support.v4.app.Fragment childFragment = new SessionFragment();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.countdown_tab, childFragment).commit();
            }
        }.start();
        try {
            Date date = sdf.parse(TilesFragment.timePanelTimeValue);
            dateTime.setTime(date);
        } catch (ParseException e) {
            System.out.println(e);
        }
        long x = dateTime.get(Calendar.HOUR_OF_DAY) * 60 * 60;
        long y = dateTime.get(Calendar.MINUTE) * 60;
        long z = dateTime.get(Calendar.SECOND);
        long progressBarTime = (x + y + z) * 1000;
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == countDownStartButton) {
            android.support.v4.app.Fragment childFragment = new SessionFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.countdown_tab, childFragment).commit();
        }
        if (v == countDownCancelButton) {
            android.support.v4.app.Fragment childFragment = new TilesFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.countdown_tab, childFragment).commit();
        }
    }
}
