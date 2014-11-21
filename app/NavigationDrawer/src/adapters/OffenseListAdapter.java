//package adapters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.TextView;
//
//import com.RSMSA.policeApp.MainActivity;
//import com.RSMSA.policeApp.Model;
//import com.RSMSA.policeApp.OffenseListActivity;
//import com.RSMSA.policeApp.R;
//
//import java.util.List;
//
///**
// * Created by Isaiah on 8/25/2014.
// */
//public class OffenseListAdapter extends ArrayAdapter<Model> {
//
//    /**
//     * define class variable
//     */
//    private final Activity mContext;
//    private final List<String> mList;
//    public boolean checker;
//    boolean checkAll_flag = false;
//    boolean checkItem_flag = false;
//    public int count = 0;
//    public int val;
//    public int size;
//
//    /**
//     *
//     * @param context
//     * @param list
//     */
////    public OffenseListAdapter(Activity context,List<String> list){
////
////        mContext = context;
////        mList = list;
////        size = 0;
////
////    }
//
//
//    static class ViewHolder{
//        public TextView number;
//        public TextView description;
//        public CheckBox checkBox;
//    }
//
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        val = i;
//        ViewHolder viewHolder = null;
//        if(view == null){
//            LayoutInflater inflator = mContext.getLayoutInflater();
//            //LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflator.inflate(R.layout.offense, null);
//            viewHolder = new ViewHolder();
//            viewHolder.checkBox = (CheckBox)view.findViewById(R.id.offense_checked);
//            viewHolder.number = (TextView)view.findViewById(R.id.offense_no);
//            viewHolder.description = (TextView)view.findViewById(R.id.offense_description);
//
//            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                    checker = isChecked;
//                    int getPosition = (Integer)compoundButton.getTag();  // Here we get the position that we have set for the checkbox using setTag.
//                    mList.get(getPosition).setSelected(compoundButton.isChecked()); // Set the value of checkbox to maintain its state.
//
//                    if (checker) {
//                        OffenseListActivity.offenseDesc.add(mList.get(val));
//                        OffenseListActivity.offenseCount++;
//                    }
//                }
//            });
//
//            view.setTag(viewHolder);
//            view.setTag(R.id.offense_checked, viewHolder.checkBox);
//            view.setTag(R.id.offense_description, viewHolder.description);
//            view.setTag(R.id.offense_no, viewHolder.number);
//        }
//        else{
//            viewHolder = (ViewHolder)view.getTag();
//        }
//
//        viewHolder.checkBox.setTag(i);
//
//        viewHolder.number.setTypeface(MainActivity.Rosario_Regular);
//        viewHolder.number.setText(""+(i+1));
//
//        viewHolder.description.setTypeface(MainActivity.Rosario_Regular);
//        viewHolder.description.setText(mList.get(i));
//
//        count++;
//        return view;
//    }
//
//}
//
