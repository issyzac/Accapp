package com.rsmsa.accapp;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

/**
 *  Created by isaiah on 10/23/2014.
 */
public class AtcSelect extends Activity {

    String SelectedItem;

    TextView SelItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        setContentView(R.layout.atc_select);



        final Bundle bundle=getIntent().getExtras();
        SelectedItem = bundle.getString("classification");

        SelItem = (TextView)findViewById(R.id.selected_item);
        SelItem.setText(SelectedItem);

    }

}
