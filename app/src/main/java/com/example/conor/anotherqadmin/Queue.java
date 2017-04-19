package com.example.conor.anotherqadmin;



//References:
// http://stackoverflow.com/questions/38551013/android-firebase-retrieve-data-from-child-node
//https://www.youtube.com/playlist?list=PLGCjwl1RrtcSi2oV5caEVScjkM6r3HO9t - TVAC Studios
//
//

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Queue extends AppCompatActivity {

    private ListView lQueue;
    private DatabaseReference mDatabase;
    private Button bNext;

    private ArrayList<String> mName = new ArrayList<>();
    private ArrayList<String> mKey = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        bNext = (Button) findViewById(R.id.bNext);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.keepSynced(true);
        lQueue = (ListView) findViewById(R.id.lQueue);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, mName);
        lQueue.setAdapter(arrayAdapter);

       mDatabase.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {

               String value = dataSnapshot.getValue(String.class);
               mName.add(value);

               String key = dataSnapshot.getKey();
               mKey.add(key);

               arrayAdapter.notifyDataSetChanged();

           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {

               String value = dataSnapshot.getValue(String.class);
               String key = dataSnapshot.getKey();

               int index = mKey.indexOf(key);
               mName.set(index, value);

               arrayAdapter.notifyDataSetChanged();
           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });




    }
}
