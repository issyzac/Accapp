package com.rsmsa.accapp;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

/**
 *  Created by isaiah on 10/20/2014.
 */
public class ScreenSlidePageFragmentTwo extends Fragment {

    TextView tab_one;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.vehicle_two, container, false);

       // tab_one = (TextView) rootView.findViewById(R.id.vehicle_one_tab);
        Log.d("vp", "current page " + MainActivity.currentPage);
       // Log.d("vp", "tab text "+tab_one.getText());


        return rootView;
    }
}