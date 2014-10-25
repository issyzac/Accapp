package adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsmsa.accapp.R;

import java.util.List;

/**
 *  Created by isaiah on 10/24/2014.
 */
public class SpinnerAdapter  extends BaseAdapter {

    Context c;
    List<String> objects;

    public SpinnerAdapter(Context context, List<String> objects) {
        super();
        this.c = context;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        String cur_obj = objects.get(position);
        LayoutInflater inflater = ((Activity) c).getLayoutInflater();
        View row = inflater.inflate(R.layout.spinner, parent, false);

        TextView spinnerText = (TextView) row.findViewById(R.id.spinner_text);
        spinnerText.setText(cur_obj);

        return row;
    }
}