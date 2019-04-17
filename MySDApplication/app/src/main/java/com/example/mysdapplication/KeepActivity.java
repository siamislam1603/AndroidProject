package com.example.mysdapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class KeepActivity extends AppCompatActivity{
    Button noteButton;
    Button signOut;
    String title="",note="";
    int addNote=0;
    String userGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep);
        this.setTitle("Google Keep");
        noteButton=(Button)findViewById(R.id.notesId);
        signOut=(Button)findViewById(R.id.signOutButtonId);
        Intent i=getIntent();
        userGet=i.getStringExtra("userFound");
    }


    public void noteOnClick(View view) {
        Intent i=new Intent(this,NoteActivity.class);
        i.putExtra("userFound",userGet);
        startActivity(i);
    }

    public void signOutOnClick(View view) {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void noteButtonOnClick(View view) {
        Intent i=new Intent(KeepActivity.this,NoteListActivity.class);
        i.putExtra("userFound",userGet);
        startActivity(i);
    }

    public void checkBoxButtonOnClick(View view) {
        Intent i=new Intent(KeepActivity.this, ChcekBoxActivity.class);
        i.putExtra("userFound",userGet);
        startActivity(i);
    }

    public void reminderOnClick(View view) {
        Intent i=new Intent(KeepActivity.this,ReminderActivity.class);
        i.putExtra("userFound",userGet);
        startActivity(i);
    }

    public void showCheckBoxButtonOnClick(View view) {
        Intent i=new Intent(KeepActivity.this,ShareActivity.class);
        i.putExtra("userFound",userGet);
        startActivity(i);
    }

    public void drawOnClick(View view) {
        Intent i=new Intent(KeepActivity.this,DrawActivity.class);
        i.putExtra("userFound",userGet);
        startActivity(i);
    }
}
