package com.hazyaz.ctup.PrivateChat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.hazyaz.ctup.R;

public class PrivateChat extends AppCompatActivity {


    private DatabaseReference mPrivatedatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_chat);


        String UserId = getIntent().getStringExtra("user_id");
        String UserName = getIntent().getStringExtra("user_name");


    }
}
