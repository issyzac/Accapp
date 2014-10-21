package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rsmsa.accapp.MainActivity;
import com.rsmsa.accapp.R;

/**
 *  Created by isaiah on 10/21/2014.
 */

public class VehicleOnePassengerOneScreenSlide extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.one_pass_one, container, false);

        return rootView;
    }
}
