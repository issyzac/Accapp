package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsmsa.accapp.R;

/**
 * Created by gogo on 10/21/2014.
 */
public class VehicleOnePassengerTwo extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.one_pass_two, container, false);

        return rootView;
    }
}

