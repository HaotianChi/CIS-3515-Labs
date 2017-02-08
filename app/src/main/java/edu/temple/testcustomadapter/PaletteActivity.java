package edu.temple.testcustomadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class PaletteActivity extends Activity {
    private Spinner spinner;
    private List<Map<String, Object>> colorList;
    boolean isSpinnerFirst = true;
    String [] colorArray = {"Red", "Blue", "Green", "White", "Yellow"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        spinner = (Spinner) findViewById(R.id.spinner);
        colorList = getData();
        MyAdapter adapter = new MyAdapter(this);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
            int arg2, long arg3){
                View myLayout = findViewById(R.id.linear_layout);
                if(isSpinnerFirst){
                    arg0.setSelection(3);
                    //arg0.setVisibility(View.INVISIBLE);
                    //myLayout.setBackgroundColor(Color.WHITE);
                    isSpinnerFirst = false;
                }
                else {
                    String chosenColor = colorArray[arg2];
                    myLayout.setBackgroundColor(Color.parseColor(chosenColor));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private List<Map<String, Object>> getData()
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;

        Object [] objArray = {Color.RED, Color.BLUE, Color.GREEN, Color.WHITE, Color.YELLOW};
        for(int i = 0; i < 5; i++) {
            map = new HashMap<String, Object>();
            map.put("color", colorArray[i]);
            map.put("obj", objArray[i]);
            list.add(map);
        }

        return list;
    }

    //ViewHolder
    static class ViewHolder
    {
        public TextView color;
    }

    public class MyAdapter extends BaseAdapter
    {
        private LayoutInflater mInflater = null;
        private MyAdapter(Context context)
        {
            this.mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            //How many items are in the data set represented by this Adapter.
            return colorList.size();
        }

        @Override
        public Object getItem(int position) {
            // Get the data item associated with the specified position in the data set.
            return position;
        }

        @Override
        public long getItemId(int position) {
            //Get the row id associated with the specified position in the list.
            return position;
        }

        //Get a View that displays the data at the specified position in the data set.
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if(convertView == null)
            {
                holder = new ViewHolder();

                convertView = mInflater.inflate(R.layout.spinner_item, null);
                holder.color = (TextView)convertView.findViewById(R.id.color);
                convertView.setTag(holder);
            }else
            {
                holder = (ViewHolder)convertView.getTag();
            }


            holder.color.setText((String)colorList.get(position).get("color"));
            holder.color.setBackgroundColor((int)colorList.get(position).get("obj"));

            return convertView;
        }

    }
}