package com.example.mysdapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText userName,passWord;
    Button logInButton;
    Button signUpButton;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Google Keep");
        userName=(EditText) findViewById(R.id.signInUserNameId);
        passWord=(EditText) findViewById(R.id.signInPasswordId);
        logInButton=(Button) findViewById(R.id.loginButtonId);
        signUpButton=(Button)findViewById(R.id.SignUpbutton);
        myDb=new DatabaseHelper(this,"GoogleKeep.db");
    }

    public void logInOnClick(View view) {
        verifyUser();
    }

    public void SignUpOnClick(View view) {
        Intent i=new Intent(MainActivity.this,SignUpActivity.class);
        startActivity(i);
    }
    public void verifyUser(){
        Cursor res=myDb.getAllData();
        if(res.getCount()==0){
            Toast.makeText(this,"User not found",Toast.LENGTH_LONG).show();
        }
        else{
            while(res.moveToNext()){
                if(res.getString(1).equals(userName.getText().toString()) && res.getString(2).equals(passWord.getText().toString())){
                    String userFound=userName.getText().toString()+"_"+passWord.getText().toString()+".db";
                    Intent i=new Intent(MainActivity.this,KeepActivity.class);
                    i.putExtra("userFound",userFound);
                    startActivity(i);
                    break;
                }
            }
        }
    }
}
