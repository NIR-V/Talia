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

public class Lane5 extends AppCompatActivity {

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
    EditText intensity6;
    EditText stroke6;
    EditText description6;

    EditText minutes7;
    EditText distance7;
    EditText intensity7;
    EditText stroke7;
    EditText description7;

    EditText minutes8;
    EditText distance8;
    EditText intensity8;
    EditText stroke8;
    EditText description8;

    EditText name1Set1;
    EditText reps1Set1;
    EditText distance1Set1;
    EditText stroke1Set1;
    EditText goal1Set1;
    EditText interval1Set1;

    EditText name2Set1;
    EditText reps2Set1;
    EditText distance2Set1;
    EditText stroke2Set1;
    EditText goal2Set1;
    EditText interval2Set1;

    EditText name3Set1;
    EditText reps3Set1;
    EditText distance3Set1;
    EditText stroke3Set1;
    EditText goal3Set1;
    EditText interval3Set1;

    EditText name4Set1;
    EditText reps4Set1;
    EditText distance4Set1;
    EditText stroke4Set1;
    EditText goal4Set1;
    EditText interval4Set1;

    EditText name5Set1;
    EditText reps5Set1;
    EditText distance5Set1;
    EditText stroke5Set1;
    EditText goal5Set1;
    EditText interval5Set1;

    EditText name6Set1;
    EditText reps6Set1;
    EditText distance6Set1;
    EditText stroke6Set1;
    EditText goal6Set1;
    EditText interval6Set1;

    EditText name7Set1;
    EditText reps7Set1;
    EditText distance7Set1;
    EditText stroke7Set1;
    EditText goal7Set1;
    EditText interval7Set1;

    EditText name8Set1;
    EditText reps8Set1;
    EditText distance8Set1;
    EditText stroke8Set1;
    EditText goal8Set1;
    EditText interval8Set1;


    EditText name1Set2;
    EditText reps1Set2;
    EditText distance1Set2;
    EditText stroke1Set2;
    EditText goal1Set2;
    EditText interval1Set2;

    EditText name2Set2;
    EditText reps2Set2;
    EditText distance2Set2;
    EditText stroke2Set2;
    EditText goal2Set2;
    EditText interval2Set2;

    EditText name3Set2;
    EditText reps3Set2;
    EditText distance3Set2;
    EditText stroke3Set2;
    EditText goal3Set2;
    EditText interval3Set2;

    EditText name4Set2;
    EditText reps4Set2;
    EditText distance4Set2;
    EditText stroke4Set2;
    EditText goal4Set2;
    EditText interval4Set2;

    EditText name5Set2;
    EditText reps5Set2;
    EditText distance5Set2;
    EditText stroke5Set2;
    EditText goal5Set2;
    EditText interval5Set2;

    EditText name6Set2;
    EditText reps6Set2;
    EditText distance6Set2;
    EditText stroke6Set2;
    EditText goal6Set2;
    EditText interval6Set2;

    EditText name7Set2;
    EditText reps7Set2;
    EditText distance7Set2;
    EditText stroke7Set2;
    EditText goal7Set2;
    EditText interval7Set2;

    EditText name8Set2;
    EditText reps8Set2;
    EditText distance8Set2;
    EditText stroke8Set2;
    EditText goal8Set2;
    EditText interval8Set2;


    EditText name1Set3;
    EditText reps1Set3;
    EditText distance1Set3;
    EditText stroke1Set3;
    Edi