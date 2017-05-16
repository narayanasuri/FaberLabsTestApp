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

/**
 * Created by Admin on 4/29/2017.
 */

public class SessionFragment extends Fragment implements View.OnClickListener {

    Button endSessionButton;
    TextView sessionTabExpandtv;
    ScrollView sessionTabLayout;
    ImageView sessionCondenseImg, sessionImage2, sessionImage3;


    public static SessionFragment newInstance() {
        return new SessionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.session_fragment, container, false);
        endSessionButton = (Button) view.findViewById(R.id.endsessionbtn);
        endSessionButton.setOnClickListener(this);
        sessionTabExpandtv = (TextView) view.findViewById(R.id.training_tab_expandtv);
        sessionTabExpandtv.setOnClickListener(this);

        sessionTabLayout = (ScrollView) view.findViewById(R.id.session_tab);

        sessionCondenseImg = (ImageView) view.findViewById(R.id.condense_session_img);
        sessionCondenseImg.setOnClickListener(this);
        sessionImage2 = (ImageView) view.findViewById(R.id.session_img2);
        sessionImage3 = (ImageView) view.findViewById(R.id.session_img3);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == endSessionButton) {
            android.support.v4.app.Fragment childFragment = new TilesFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(R.id.session_framelayout, childFragment).commit();
        }

        if (v == sessionTabExpandtv) {
            sessionTabExpandtv.setVisibility(View.GONE);
            sessionCondenseImg.setVisibility(View.VISIBLE);
            sessionImage2.setVisibility(View.VISIBLE);
            sessionImage3.setVisibility(View.VISIBLE);
            sessionTabLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //replace this line to scroll up or down
                    sessionTabLayout.fullScroll(ScrollView.FOCUS_DOWN);
                }
            }, 100L);
        }

        if (v == sessionCondenseImg) {
            sessionTabExpandtv.setVisibility(View.VISIBLE);
            sessionCondenseImg.setVisibility(View.GONE);
            sessionImage2.setVisibility(View.GONE);
            sessionImage3.setVisibility(View.GONE);
        }
    }
}
