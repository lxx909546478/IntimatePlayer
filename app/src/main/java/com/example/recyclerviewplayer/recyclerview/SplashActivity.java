package com.example.recyclerviewplayer.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import com.example.recyclerviewplayer.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

/**
    app封面信息页，设置一个线程在五秒后跳转至主页面
    创建人：钟健
    时间：2020.5.28
 **/

public class SplashActivity extends AppCompatActivity {

    private static final String TEG="zhongjian";

    ResourceParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //状态栏隐藏
        getSupportActionBar().hide(); //标题栏隐藏
        setContentView(R.layout.activity_splash);
        parser=new ResourceParser(0);
        Thread myThread = new Thread(){//创建子线程，五秒后跳转至主页面
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
