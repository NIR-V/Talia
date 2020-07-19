/**
 * @author Abir Haque
 * @version 1.0
 * @since 2020-07-12
 */
package com.hawk.tallia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayProfile extends Activity {

    EditText name;
    Switch spectrum;
    TextView guardian_label;
    TextView guardian_name_label;
    TextView guardian_number_label;
    EditText guardian_name;
    EditText guardian_number;
    TableRow guardianr1;
    TableRow guardianr2;
    TableRow guardianr3;
    TableRow guardianr4;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);

        loadData();

        name = (EditText) findViewById(R.id.editTextFirstName);
        spectrum = (Switch) findViewById(R.id.autistic_switch);
        guardian_label = (TextView) findViewById(R.id.textViewDate);
        guardian_name_label = (TextView) findViewById(R.id.textViewDate);
        guardian_number_label = (TextView) findViewById(R.id.textViewDate);
        guardian_name = (EditText) findViewById(R.id.editTextParentName);
        guardian_number = (EditText) findViewById(R.id.editTextPhoneNumber);
        guardianr1 = (TableRow) findViewById(R.id.guardian1);
        guardianr2 = (TableRow) findViewById(R.id.guardian2);
        guardianr3 = (TableRow) findViewById(R.id.guardian3);
        guardianr4 = (TableRow) findViewById(R.id.guardian4);
        save = (Button) findViewById(R.id.buttonSave);
        guardianr4.setVisibility(View.GONE);

        spectrum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(spectrum.isChecked()){
                    spectrum_text = "1";
                    guardianr1.setVisibility(View.VISIBLE);
                    guardianr2.setVisibility(View.VISIBLE);
                    guardianr3.setVisibility(View.VISIBLE);
                    guardianr4.setVisibility(View.GONE);
                }
                else
                {
                    spectrum_text = "0";
                    guardianr1.setVisibility(View.GONE);
                    guardianr2.setVisibility(View.GONE);
                    guardianr3.setVisibility(View.GONE);
                    guardianr4.setVisibility(View.GONE);
                }
            }
        });
        if (spectrum_text.equals("1"))
        {
            guardianr1.setVisibility(View.VISIBLE);
            guardianr2.setVisibility(View.VISIBLE);
            guardianr3.setVisibility(View.VISIBLE);
        }
        else
        {
            guardianr1.setVisibility(View.GONE);
            guardianr2.setVisibility(View.GONE);
            guardianr3.setVisibility(View.GONE);
        }
        updateViews();
    }
    public boolean isRealNumber(String number)
    {
        if (number.length() > 15)
        {
            return false;
        }
        return true;
    }
    public void saveData(View v)
    {
        if (spectrum_text.equals("0"))
        {
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(NAME, name.getText().toString());
            spectrum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (spectrum.isChecked()) {
                        spectrum_text = "1";
                    } else {
                        spectrum_text = "0";
                    }
                }
            });
            editor.putString(SPECTRUM, spectrum_text);
            editor.putString(PARENT_NAME, guardian_name.getText().toString());
            editor.putString(PARENT_NUMBER, guardian_number.getText().toString());
            editor.apply();
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        if(spectrum_text.equals("1") && isRealNumber(guardian_number.getText().toString()))
        {
            try
            {
                SmsManager smsManager = SmsManager.getDefault();
                String number = guardian_number.getText().toString();
                String message = "Hello, " + guardian_name.getText().toString() + ".\nThis is a test message from " + name.getText().toString() + ". If this is not you, please ignore this message. Thank you! \n -The Tallia App";
                smsManager.sendTextMessage(number, null, message, null, null);
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(NAME, name.getText().toString());
                spectrum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (spectrum.isChecked()) {
                            spectrum_text = "1";
                        } else {
                            spectrum_text = "0";
                        }
                    }
                });
                editor.putString(SPECTRUM, spectrum_text);
                editor.putString(PARENT_NAME, guardian_name.getText().toString());
                editor.putString(PARENT_NUMBER, guardian_number.getText().toString());
                editor.apply();
                Toast.makeText(getApplicationContext(), "Saved. Test message sent", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
            catch(Exception e)
            {
                guardianr4.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            guardianr4.setVisibility(View.VISIBLE);
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
    public void updateViews()
    {
        name.setText(name_text);
        if (spectrum_text.equals("1"))
        {
            spectrum.setChecked(true);
        }
        else
        {
            spectrum.setChecked(false);
        }
        guardian_name.setText(guardian_name_text);
        guardian_number.setText(guardian_number_text);
    }
}
