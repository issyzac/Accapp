package adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.rsmsa.accapp.MainOffence;
import com.rsmsa.accapp.OffenseListActivity;
import com.rsmsa.accapp.R;

import java.util.List;

/**
 *  Created by Isaiah on 9/2/2014.
 */
public class adapter extends ArrayAdapter<String> {

    /**
     * define class variable
     */
    private final Activity mContext;
    private final List<String> mList;
    private final List<String> mrelList;
    public boolean[] checked;
    public int mSize = 0;
    public int checkedcount;

    public adapter(Activity context, List<String> list, List<String> relList) {
        super(context, R.layout.offense, list);
        mContext = context;
        mList = list;
        mrelList = relList;
        mSize = list.size();
        checkedcount = 0;
        checked = new boolean[list.size()];
        for(int i=0; i<list.size(); i++){
            checked[i] = false;
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View cv;
        if(convertView == null){

                LayoutInflater inflator = mContext.getLayoutInflater();
                cv = new View(mContext);
                cv = inflator.inflate(R.layout.offense, null);
        }
        else {
            cv = (View) convertView;
        }

        CheckBox checkBox = (CheckBox)cv.findViewById(R.id.offense_checked);
        TextView number = (TextView)cv.findViewById(R.id.offense_no);
        TextView description = (TextView)cv.findViewById(R.id.offense_description);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                {
                    checked[position] = true;
                    OffenseListActivity.offenseDesc.add(mList.get(position));
                    OffenseListActivity.OffenseListType.add(mrelList.get(position));
                    //checkedcount++;
                    OffenseListActivity.offenseCount++;
                }
                else
                {
                    checked[position] = false;
                    OffenseListActivity.offenseDesc.remove(mList.get(position));
                    OffenseListActivity.OffenseListType.remove(mrelList.get(position));
                    //checkedcount--;
                    OffenseListActivity.offenseCount--;
                }
            }
        });

        checkBox.setChecked(checked[position]);

        number.setTypeface(MainOffence.Rosario_Regular);
        number.setText(""+(position+1));

       // OffenseListActivity.offenseCount = checkedcount;

        description.setTypeface(MainOffence.Rosario_Regular);
        description.setText(mList.get(position));

        return cv;
    }

}
