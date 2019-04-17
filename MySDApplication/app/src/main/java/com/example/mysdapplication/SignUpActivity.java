package com.example.mysdapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText userName,userPassword,emailId;
    Button saveUserDetails;
    DatabaseHelper userDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign Up");
        userName=(EditText) findViewById(R.id.SignUpuserNameId);
        emailId=(EditText)findViewById(R.id.emailId);
        userPassword=(EditText)findViewById(R.id.SignUpPasswordId);
        saveUserDetails=(Button)findViewById(R.id.signUpButtonId);
        myDb=new DatabaseHelper(this,"GoogleKeep.db");
    }

    public void SignUpUserOnCLick(View view) {
        boolean isInserted = myDb.insertData(userName.getText().toString(),userPassword.getText().toString(),
                userName.getText().toString()+"_"+userPassword.getText().toString());
        Toast.makeText(this,isInserted+"",Toast.LENGTH_LONG).show();
        userDb = new DatabaseHelper(this,userName.getText().toString()+"_"+userPassword.getText().toString()+".db");
        if(isInserted==true){
            String userFound=userName.getText().toString()+"_"+userPassword.getText().toString()+".db";
            Intent i=new Intent(SignUpActivity.this,KeepActivity.class);
            i.putExtra("userFound",userFound);
            startActivity(i);
        }
    }
}
