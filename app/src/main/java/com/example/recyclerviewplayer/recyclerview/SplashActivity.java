package com.example.recyclerviewplayer.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import com.example.recyclerviewplayer.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //状态栏隐藏
        getSupportActionBar().hide(); //标题栏隐藏
        setContentView(R.layout.activity_splash);
        Thread myThread = new Thread(){//创建子线程
            @Override
            public void run(){
                try{
                    sleep(3000);
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
