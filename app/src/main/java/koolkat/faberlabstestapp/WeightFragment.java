package koolkat.faberlabstestapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Admin on 4/29/2017.
 */

public class WeightFragment extends Fragment implements View.OnClickListener {

    Button startbtn, cancelbtn;

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
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==startbtn)
            Toast.makeText(getContext(), "Timer started!", Toast.LENGTH_SHORT).show();
        if(v==cancelbtn)
            Toast.makeText(getContext(), "Timer stopped!", Toast.LENGTH_SHORT).show();
    }
}
