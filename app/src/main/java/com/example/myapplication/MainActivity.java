package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,new ListFragment());
        transaction.commit();
        Log.d("Main","INja okeye 1");

        final SharedPreferences share = getSharedPreferences("prefActivity",MODE_PRIVATE);
        final SharedPreferences.Editor editor = share.edit();
        boolean isFirstRun = share.getBoolean("FIRSTRUN",true);
        if (isFirstRun) {
            startActivity(new Intent(MainActivity.this,Sharepref.class));
            editor.putBoolean("FIRSTRUN",false);
            editor.commit();
        }



    }
}