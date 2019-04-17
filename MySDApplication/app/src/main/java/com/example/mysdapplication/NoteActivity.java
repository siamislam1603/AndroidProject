package com.example.mysdapplication;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    EditText title,note;
    Button backPressSave;
    DatabaseHelper userDb;
    DatabaseHelper myDb;
    String userGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        title=(EditText)findViewById(R.id.titleId);
        note=(EditText)findViewById(R.id.noteId);
        backPressSave=(Button)findViewById(R.id.buttonBackPressed);
        Intent i=getIntent();
        userGet=i.getStringExtra("userFound");
        userDb = new DatabaseHelper(this,userGet);
    }

    public void backPressedOnClick(View view) {
        if(!title.getText().toString().equals("") && !note.getText().toString().equals("")){
                boolean isInserted = userDb.insertData(title.getText().toString(),note.getText().toString());
                Toast.makeText(this,isInserted+"",Toast.LENGTH_LONG).show();
                if(isInserted==true){
                    Intent i=new Intent(NoteActivity.this,KeepActivity.class);
                    i.putExtra("userFound",userGet);
                    startActivity(i);
                }
        }
    }

}
