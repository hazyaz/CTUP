package com.hazyaz.ctup.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hazyaz.ctup.MainActivity;
import com.hazyaz.ctup.R;

public class ExistingUserLogin extends Activity {
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_user_login);

        final EditText mExistingUser = (EditText)findViewById(R.id.ExisEmail);
        final EditText mExistingPassword = (EditText)findViewById(R.id.ExisPass);
        Button mExistingUserLogin = (Button)findViewById(R.id.ExinstingUserLogin);
        mExistingUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mUserName = mExistingUser.getEditableText().toString();
                String mUserPassword = mExistingPassword.getEditableText().toString();

                loginUser(mUserName,mUserPassword);
            }
        });

    }

    private void loginUser(String mUserName, String mUserPassword) {

        mAuth.signInWithEmailAndPassword(mUserName,mUserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful()) {
    Intent intent = new Intent(ExistingUserLogin.this, MainActivity.class);
    startActivity(intent);
    finish();
}
    else
    {
        Toast.makeText(getApplicationContext(),"No id found",Toast.LENGTH_SHORT).show();

    }
            }
        });


    }
}
