package com.hawk.swimdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Switch;

import java.util.ArrayList;

public class Lane8 extends AppCompatActivity {

    DBHelper mydb;

    LinearLayout columnNames1;
    LinearLayout columnNames2;

    Button button_ln1;
    Button button_ln2;
    Button button_ln3;
    Button button_ln4;
    Button button_ln5;
    Button button_ln6;
    Button button_ln7;
    Button button_ln8;

    Button calculate;

    TextView switchMessage;

    Switch sh1;
    Switch sh2;
    Switch sh3;
    Switch sh4;
    Switch sh5;
    Switch sh6;
    Switch sh7;
    Switch sh8;

    TableLayout set1;
    TableLayout set2;
    TableLayout set3;
    TableLayout set4;
    TableLayout set5;
    TableLayout set6;
    TableLayout set7;
    TableLayout set8;

    TableRow tr1;
    TableRow tr2;
    TableRow tr3;
    TableRow tr4;
    TableRow tr5;
    TableRow tr6;
    TableRow tr7;
    TableRow tr8;

    EditText minutes1;
    EditText distance1;
    EditText intensity1;
    EditText stroke1;
    EditText description1;

    EditText minutes2;
    EditText distance2;
    EditText intensity2;
    EditText stroke2;
    EditText description2;

    EditText minutes3;
    EditText distance3;
    EditText intensity3;
    EditText stroke3;
    EditText description3;

    EditText minutes4;
    EditText distance4;
    EditText intensity4;
    EditText stroke4;
    EditText description4;

    EditText minutes5;
    EditText distance5;
    EditText intensity5;
    EditText stroke5;
    EditText description5;

    EditText minutes6;
    EditText distance6;
    Edit