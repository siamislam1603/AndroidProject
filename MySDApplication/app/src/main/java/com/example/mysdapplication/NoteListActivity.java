package com.example.mysdapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.IccOpenLogicalChannelResponse;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NoteListActivity extends AppCompatActivity {
    String userGet;
    ListView noteList;
    DatabaseHelper userDb;
    List<NoteGet> noteGet;
    List<String> listNote=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Intent i=getIntent();
        userGet=i.getStringExtra("userFound");
        noteList=(ListView)findViewById(R.id.notesListViewId);
        userDb=new DatabaseHelper(this,userGet);
        Cursor res=userDb.getAllData();
        if(res.getCount()==0){
            return;
        }
        else{
            noteGet=new ArrayList<NoteGet>();
            while(res.moveToNext()){
                noteGet.add(new NoteGet(res.getString(0),res.getString(1),res.getString(2)));
                listNote.add(res.getString(1)+"\n"+res.getString(2));
            }
        }
        ArrayAdapter<String> noteAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listNote);
        noteList.setAdapter(noteAdapter);
        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(NoteListActivity.this,UpdateNoteActivity.class);
                i.putExtra("id",noteGet.get(position).id);
                i.putExtra("userFound",userGet);
                i.putExtra("title",noteGet.get(position).title);
                i.putExtra("note",noteGet.get(position).note);
                startActivity(i);
            }
        });
    }
}
