package koolkat.faberlabstestapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Admin on 4/29/2017.
 */

public class TrainingFragment extends Fragment implements View.OnClickListener {

    Button endSessionButton;
    TextView trainingTabExpandtv;
    ScrollView trainingTabLayout;
    ImageView settingCondenseImg, settingImage2, settingImage3;


    public static TrainingFragment newInstance() {
        return new TrainingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.training_fragment, container, false);
        endSessionButton = (Button) view.findViewById(R.id.endsessionbtn);
        endSessionButton.setOnClickListener(this);
        trainingTabExpandtv = (TextView) view.findViewById(R.id.training_tab_expandtv);
        trainingTabExpandtv.setOnClickListener(this);

        trainingTabLayout = (ScrollView) view.findViewById(R.id.training_tab);

        settingCondenseImg = (ImageView) view.findViewById(R.id.condense_img);
        settingCondenseImg.setOnClickListener(this);
        settingImage2 = (ImageView) view.findViewById(R.id.setting_img2);
        settingImage3 = (ImageView) view.findViewById(R.id.setting_img3);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == endSessionButton){
            android.support.v4.app.Fragment childFragment = new PerformanceFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.training_framelayout, childFragment).commit();
        }

        if(v == trainingTabExpandtv){
            trainingTabExpandtv.setVisibility(View.GONE);
            settingCondenseImg.setVisibility(View.VISIBLE);
            settingImage2.setVisibility(View.VISIBLE);
            settingImage3.setVisibility(View.VISIBLE);
            trainingTabLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //replace this line to scroll up or down
                    trainingTabLayout.fullScroll(ScrollView.FOCUS_DOWN);
                }
            }, 100L);
        }

        if(v == settingCondenseImg){
            trainingTabExpandtv.setVisibility(View.VISIBLE);
            settingCondenseImg.setVisibility(View.GONE);
            settingImage2.setVisibility(View.GONE);
            settingImage3.setVisibility(View.GONE);
        }
    }
}
