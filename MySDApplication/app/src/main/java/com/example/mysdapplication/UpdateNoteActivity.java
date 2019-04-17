package com.example.mysdapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNoteActivity extends AppCompatActivity {
    String id;
    String userGet;
    String title,note;
    DatabaseHelper userDb;
    EditText updateNote,updateTitle;
    Button updateButton,deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        Intent i=getIntent();
        id=i.getStringExtra("id");
        userGet=i.getStringExtra("userFound");
        title=i.getStringExtra("title");
        note=i.getStringExtra("note");
        updateButton=(Button) findViewById(R.id.updateNoteButton);
        deleteButton=(Button) findViewById(R.id.deleteNoteButton);
        updateNote=(EditText)findViewById(R.id.updateNoteId);
        updateTitle=(EditText)findViewById(R.id.updateTitleId);
        updateTitle.setText(title);
        updateNote.setText(note);
        userDb=new DatabaseHelper(this,userGet);
    }

    public void updateNoteOnClick(View view) {
        boolean isUpdate=userDb.updataData(id,updateTitle.getText().toString(),updateNote.getText().toString());
        if(isUpdate==true){
            Intent i=new Intent(UpdateNoteActivity.this,KeepActivity.class);
            i.putExtra("userFound",userGet);
            startActivity(i);
        }
        else
            Toast.makeText(this,"Not updated",Toast.LENGTH_LONG).show();
    }

    public void deleteNoteOnClick(View view) {
        int deletedRows=userDb.deleteData(id);
        if(deletedRows>0){
            Intent i=new Intent(UpdateNoteActivity.this,KeepActivity.class);
            i.putExtra("userFound",userGet);
            startActivity(i);
        }
        else{
            Toast.makeText(this,"Not deleted",Toast.LENGTH_LONG).show();
        }
    }
}
