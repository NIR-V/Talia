/**
 * @author Abir Haque
 * @version 1.0
 * @since 2020-07-12
 */
package com.hawk.tallia;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {
    public final static String INIT = "INIT";
    private ListView obj;
    DBHelper journaldb;
    Switch add_entry_switch;
    TextView message;

    String name = "";

    GraphView graph;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NAME = "name";
    public static final String SPECTRUM = "spectrum";
    public static final String PARENT_NAME = "parent_name";
    public static final String PARENT_NUMBER = "parent_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String name_text = sharedPreferences.getString(NAME, "");
        name = name_text;

        if (name.equals(""))
        {
            Intent intent = new Intent(this, DisplayProfile.class);
            startActivity(intent);
        }

        graph = findViewById(R.id.graph);
        message = findViewById(R.id.message);
        add_entry_switch = findViewById(R.id.add_entry_switch);
        add_entry_switch.setChecked(false);
        journaldb = new DBHelper(this);

        ArrayList date_list = journaldb.getAllDates();

        String today = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        if (date_list.isEmpty() == false && today.equals(date_list.get(date_list.size() - 1)) == true)
        {
            add_entry_switch.setVisibility(add_entry_switch.GONE);
            graph.setVisibility(graph.VISIBLE);
            message.setText("All set for today, " + name + ".");
        }
        else
        {
            add_entry_switch.setVisibility(add_entry_switch.VISIBLE);
            graph.setVisibility(graph.GONE);
            message.setText("Hello, " + name + ".");
        }

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, date_list);
        obj = (ListView)findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(), DisplayEntry.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
        add_entry_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( add_entry_switch.isChecked() ){
                    Bundle dataBundle = new Bundle();
                    dataBundle.putInt("id", 0);

                    Intent intent = new Intent(getApplicationContext(), DisplayEntry.class);
                    intent.putExtras(dataBundle);

                    startActivity(intent);
                }
            }
        });
        ArrayList<String> dates = journaldb.getAllDates();
        ArrayList<String> ratings = journaldb.getAllRating();
        if (dates.isEmpty() == false)
        {
            DataPoint[] data = new DataPoint[dates.size()];
            for (int i = 0; i < dates.size(); i++)
            {
                int x = dateToInt(dates.get(i));
                int y = Integer.parseInt(ratings.get(i));
                data[i] = new DataPoint(x,y);
            }
            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(data);
            graph.addSeries(series);
        }
        else
        {
            DataPoint[] data = new DataPoint[]{};
            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(data);
            graph.addSeries(series);
        }
    }
    public int dateToInt(String d)
    {
        String date = d;
        int month = Integer.parseInt(date.substring(0,2));
        int day = Integer.parseInt(date.substring(3,5));
        int year = Integer.parseInt(date.substring(6,10));
        int num = 0;
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
        {
            //leap
            if (month == 1)
            {
                num = day + 31 + 366;
            }
            if (month == 2)
            {
                num = day + 60 + 366;
            }
            if (month == 3)
            {
                num = day + 91 + 366;
            }
            if (month == 4)
            {
                num = day + 121 + 366;
            }
            if (month == 5)
            {
                num = day + 152 + 366;
            }
            if (month == 6)
            {
                num = day + 182 + 366;
            }
            if (month == 7)
            {
                num = day + 213 + 366;
            }
            if (month == 8)
            {
                num = day + 244 + 366;
            }
            if (month == 9)
            {
                num = day + 274 + 366;
            }
            if (month == 10)
            {
                num = day + 305 + 366;
            }
            if (month == 11)
            {
                num = day + 335 + 366;
            }
            if (month == 12)
            {
                num = day + 366 + 366;
            }
        }
        else
        {
            if (year % 100 == 0 && year % 400 != 0)
            {
                //not leap
                if (month == 1)
                {
                    num = day + 31 + 365;
                }
                if (month == 2)
                {
                    num = day + 59 + 365;
                }
                if (month == 3)
                {
                    num = day + 90 + 365;
                }
                if (month == 4)
                {
                    num = day + 120 + 365;
                }
                if (month == 5)
                {
                    num = day + 151 + 365;
                }
                if (month == 6)
                {
                    num = day + 181 + 365;
                }
                if (month == 7)
                {
                    num = day + 212 + 365;
                }
                if (month == 8)
                {
                    num = day + 243 + 365;
                }
                if (month == 9)
                {
                    num = day + 273 + 365;
                }
                if (month == 10)
                {
                    num = day + 304 + 365;
                }
                if (month == 11)
                {
                    num = day + 334 + 365;
                }
                if (month == 12)
                {
                    num = day + 365 + 365;
                }
            }
            else
            {
                if (year % 4 != 0)
                {
                    //not leap
                    if (month == 1)
                    {
                        num = day + 31 + 365;
                    }
                    if (month == 2)
                    {
                        num = day + 59 + 365;
                    }
                    if (month == 3)
                    {
                        num = day + 90 + 365;
                    }
                    if (month == 4)
                    {
                        num = day + 120 + 365;
                    }
                    if (month == 5)
                    {
                        num = day + 151 + 365;
                    }
                    if (month == 6)
                    {
                        num = day + 181 + 365;
                    }
                    if (month == 7)
                    {
                        num = day + 212 + 365;
                    }
                    if (month == 8)
                    {
                        num = day + 243 + 365;
                    }
                    if (month == 9)
                    {
                        num = day + 273 + 365;
                    }
                    if (month == 10)
                    {
                        num = day + 304 + 365;
                    }
                    if (month == 11)
                    {
                        num = day + 334 + 365;
                    }
                    if (month == 12)
                    {
                        num = day + 365 + 365;
                    }
                }
            }
        }
        return num;
    }
    public void profile(View v)
    {
        Intent intent = new Intent(this, DisplayProfile.class);
        startActivity(intent);
    }
}