/**
 * @author Abir Haque
 * @version 1.0
 * @since 2020-07-12
 */
package com.hawk.tallia;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DisplayEntry extends Activity {
    private DBHelper journaldb ;

    TextView date;
    Button r5;
    Button r4;
    Button r3;
    Button r2;
    Button r1;
    EditText entry;
    Button delete;
    Button save;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NAME = "name";
    public static final String SPECTRUM = "spectrum";
    public static final String PARENT_NAME = "parent_name";
    public static final String PARENT_NUMBER = "parent_number";

    SharedPreferences sharedPreferences;
    String name_text;
    String spectrum_text;
    String guardian_name_text;
    String guardian_number_text;

    String rating;

    int id_To_Update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_entry);

        loadData();

        date = (TextView) findViewById(R.id.textViewDate);
        r5 = (Button) findViewById(R.id.rating5);
        r4 = (Button) findViewById(R.id.rating4);
        r3 = (Button) findViewById(R.id.rating3);
        r2 = (Button) findViewById(R.id.rating2);
        r1 = (Button) findViewById(R.id.rating1);
        entry = (EditText) findViewById(R.id.editTextEntry);
        delete = (Button) findViewById(R.id.buttonDelete);
        save = (Button) findViewById(R.id.buttonSave);
        delete.setVisibility(delete.GONE);

        journaldb = new DBHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                Cursor rs = journaldb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String date_string = rs.getString(rs.getColumnIndex(DBHelper.DATE));
                String rating_string = rs.getString(rs.getColumnIndex(DBHelper.RATING));
                String entry_string = rs.getString(rs.getColumnIndex(DBHelper.ENTRY));

                delete.setVisibility(delete.VISIBLE);

                if (!rs.isClosed())  {
                    rs.close();
                }

                date.setText((CharSequence)date_string);
                if (rating_string.equals("5"))
                {
                    rating = rating_string;
                    r5.setTextColor(Color.WHITE);
                    r4.setTextColor(Color.BLACK);
                    r3.setTextColor(Color.BLACK);
                    r2.setTextColor(Color.BLACK);
                    r1.setTextColor(Color.BLACK);
                }
                if (rating_string.equals("4"))
                {
                    rating = rating_string;
                    r5.setTextColor(Color.BLACK);
                    r4.setTextColor(Color.WHITE);
                    r3.setTextColor(Color.BLACK);
                    r2.setTextColor(Color.BLACK);
                    r1.setTextColor(Color.BLACK);
                }
                if (rating_string.equals("3"))
                {
                    rating = rating_string;
                    r5.setTextColor(Color.BLACK);
                    r4.setTextColor(Color.BLACK);
                    r3.setTextColor(Color.WHITE);
                    r2.setTextColor(Color.BLACK);
                    r1.setTextColor(Color.BLACK);
                }
                if (rating_string.equals("2"))
                {
                    rating = rating_string;
                    r5.setTextColor(Color.BLACK);
                    r4.setTextColor(Color.BLACK);
                    r3.setTextColor(Color.BLACK);
                    r2.setTextColor(Color.WHITE);
                    r1.setTextColor(Color.BLACK);
                }
                if (rating_string.equals("1"))
                {
                    rating = rating_string;
                    r5.setTextColor(Color.BLACK);
                    r4.setTextColor(Color.BLACK);
                    r3.setTextColor(Color.BLACK);
                    r2.setTextColor(Color.BLACK);
                    r1.setTextColor(Color.WHITE);
                }
                entry.setText((CharSequence)entry_string);
            }
        }
        if ((date.getText().toString()).equals(""))
        {
            String today = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
            date.setText(today);
        }
    }

    public void delete(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0) {
                Cursor rs = journaldb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                journaldb.deleteEntry(id_To_Update,date.getText().toString(), rating, entry.getText().toString());
                if (!rs.isClosed())  {
                    rs.close();
                }
                Toast.makeText(getApplicationContext(), "Deleted",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }
    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            int Value = extras.getInt("id");
            if(Value>0){
                if(journaldb.updateEntry(id_To_Update,date.getText().toString(), rating, entry.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "Not updated", Toast.LENGTH_SHORT).show();
                }
            } else{
                if(journaldb.insertEntry(date.getText().toString(), rating,
                        entry.getText().toString())){
                    if(spectrum_text.equals("1"))
                    {
                        SmsManager smsManager = SmsManager.getDefault();
                        String number = guardian_number_text;
                        String message = "---Tallia Entry---\nDate: " + date.getText().toString() + "\nRating: " + rating + "/5" + "\n" + entry.getText().toString();
                        smsManager.sendTextMessage(number, null, message, null, null);
                    }
                    Toast.makeText(getApplicationContext(), "Done",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "Not done",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        }
    }
    public void loadData()
    {
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        name_text = sharedPreferences.getString(NAME, "");
        spectrum_text = sharedPreferences.getString(SPECTRUM, "0");
        guardian_name_text = sharedPreferences.getString(PARENT_NAME, "");
        guardian_number_text = sharedPreferences.getString(PARENT_NUMBER, "");
    }
    public void setRating5(View v)
    {
        rating = "5";
        r5.setTextColor(Color.WHITE);
        r4.setTextColor(Color.BLACK);
        r3.setTextColor(Color.BLACK);
        r2.setTextColor(Color.BLACK);
        r1.setTextColor(Color.BLACK);
    }
    public void setRating4(View v)
    {
        rating = "4";
        r5.setTextColor(Color.BLACK);
        r4.setTextColor(Color.WHITE);
        r3.setTextColor(Color.BLACK);
        r2.setTextColor(Color.BLACK);
        r1.setTextColor(Color.BLACK);
    }
    public void setRating3(View v)
    {
        rating = "3";
        r5.setTextColor(Color.BLACK);
        r4.setTextColor(Color.BLACK);
        r3.setTextColor(Color.WHITE);
        r2.setTextColor(Color.BLACK);
        r1.setTextColor(Color.BLACK);
    }
    public void setRating2(View v)
    {
        rating = "2";
        r5.setTextColor(Color.BLACK);
        r4.setTextColor(Color.BLACK);
        r3.setTextColor(Color.BLACK);
        r2.setTextColor(Color.WHITE);
        r1.setTextColor(Color.BLACK);
    }
    public void setRating1(View v)
    {
        rating = "1";
        r5.setTextColor(Color.BLACK);
        r4.setTextColor(Color.BLACK);
        r3.setTextColor(Color.BLACK);
        r2.setTextColor(Color.BLACK);
        r1.setTextColor(Color.WHITE);
    }

}