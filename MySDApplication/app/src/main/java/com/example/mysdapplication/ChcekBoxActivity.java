package com.example.mysdapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ChcekBoxActivity extends AppCompatActivity {
    TableLayout tableLayout;
    Button buttonEnter;
    TableRow tableRow;
    TextView t;
    EditText editText1,editText2,editText3;
    int it=1;
    CheckBox chkBox;
    String userGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chcek_box);
        tableLayout=(TableLayout)findViewById(R.id.tableLayout);
        buttonEnter=(Button) findViewById(R.id.buttonEnter);
        Intent i=getIntent();
        userGet=i.getStringExtra("userFound");
    }

    public void onClick(View view) {
        if(it!=1 && (editText1.getText().toString().equals(""))){
            Toast.makeText(this,"Make sure you've filled up all the columns", Toast.LENGTH_LONG).show();
        }
        else {
            tableRow = new TableRow(this);
            editText1 = new EditText(this);
            chkBox=new CheckBox(this);
            editText1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
            tableRow.addView(chkBox);
            tableRow.addView(editText1);
            tableLayout.addView(tableRow);
        }
    }
}
