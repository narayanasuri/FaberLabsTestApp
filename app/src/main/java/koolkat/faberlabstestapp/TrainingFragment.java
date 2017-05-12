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

public class TrainingFragment extends Fragment implements View.OnClickListener {


    public static TrainingFragment newInstance() {
        return new TrainingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.training_fragment, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {
    }
}
