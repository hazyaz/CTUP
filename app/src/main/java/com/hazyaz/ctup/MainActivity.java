package com.hazyaz.ctup;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hazyaz.ctup.login.StartingActivity;
import com.hazyaz.ctup.menu_item.Setting_activity;


public class MainActivity extends AppCompatActivity {
    TabLayout mTabLayout;
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    int RC_SIGN_IN = 0;
    //Fire base variable declaration
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       mViewPager = (ViewPager) findViewById(R.id.view_pager_main);
       mCustomPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mCustomPagerAdapter);


        mTabLayout  = (TabLayout)findViewById(R.id.tab_bar_main);
       mTabLayout.setupWithViewPager(mViewPager);


        // Fire base Class initialising of obejct
        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
// if user is not signed in send it to login screen
        Intent mStartIntent = new Intent(MainActivity.this, StartingActivity.class);
        startActivity(mStartIntent);
        finish();
        } else {

      Toast.makeText(this,"Signed in Successfully",Toast.LENGTH_LONG).show();
        }

    }


    // menu item for signout procress
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    // menu item for signout procress
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       switch (item.getItemId())
        {
            case R.id.log_out_from_main:
         FirebaseAuth.getInstance().signOut();
         sendToStart();
           return true;



            case R.id.status_setting:
           Intent SettingIntent = new Intent(MainActivity.this, Setting_activity.class);
            startActivity(SettingIntent);


           default:
               return  super.onOptionsItemSelected(item);
        }
    }
    // menu item for signout procress


void sendToStart()
{
    Intent intent = new Intent(MainActivity.this,StartingActivity.class);
    startActivity(intent);
    finish();
}


}
