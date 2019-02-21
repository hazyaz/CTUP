package com.hazyaz.ctup.chat_fragments;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.hazyaz.ctup.R;

public class ChatActivity extends AppCompatActivity {


private String mChatUser;
private DatabaseReference mRootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);



mChatUser = getIntent().getStringExtra("user_id");


mRootRef.child("users").child(mChatUser).addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

  //      String Chat_user_name = dataSnapshot.child("name")
   //     getSupportActionBar().setTitle();

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});







    }
}
