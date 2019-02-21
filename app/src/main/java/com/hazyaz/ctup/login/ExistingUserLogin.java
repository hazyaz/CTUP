package com.hazyaz.ctup.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.hazyaz.ctup.MainActivity;
import com.hazyaz.ctup.R;

public class ExistingUserLogin extends Activity {
    private FirebaseAuth mAuth;

    private TextInputLayout mExistingUser;
    private TextInputLayout mExistingPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_user_login);

        mAuth = FirebaseAuth.getInstance();

        mExistingUser = (TextInputLayout) findViewById(R.id.ExisEmail);
        mExistingPassword = (TextInputLayout) findViewById(R.id.ExisPass);
        Button mExistingUserLogin = (Button) findViewById(R.id.ExinstingUserLogin);





        mExistingUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mUserName = mExistingUser.getEditText().getText().toString();
                String mUserPassword = mExistingPassword.getEditText().getText().toString();

                loginUser(mUserName, mUserPassword);
            }
        });

    }

    private void loginUser(String mUserName, String mUserPassword) {

        mAuth.signInWithEmailAndPassword(mUserName, mUserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                 //   String mCurrentUser = mAuth.getCurrentUser().getUid();

                 //   String DeviceToken = FirebaseInstanceId.getInstance().getToken();


                            Intent intent = new Intent(ExistingUserLogin.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                }
            }
        });


    }
}