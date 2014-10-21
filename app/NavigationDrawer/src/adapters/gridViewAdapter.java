package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.rsmsa.accapp.R;


/**
 * Created by Issy on 1/19/14.
 */
public class gridViewAdapter extends BaseAdapter {
    private Context context;

    public gridViewAdapter(Context context) {
        this.context = context;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            // get layout from mobile.xml

            gridView = inflater.inflate(R.layout.empty, null);

        } else {
            gridView = (View) convertView;
        }
        // set value into textview
        return gridView;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
