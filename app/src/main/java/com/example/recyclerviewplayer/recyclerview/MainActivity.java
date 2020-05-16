package com.example.recyclerviewplayer.recyclerview;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.recyclerviewplayer.R;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RadioGroup group;
    private RadioButton mainRadio;
    private RadioButton shotRadio;
    private RadioButton albumRadio;
    private RadioButton aboutMeRadio;

    private Fragment mainFragment;
    private Fragment shootFragment;
    private Fragment albumFragment;
    private Fragment aboutMeFragment;

    static final int REQUEST_VIDEO_CAPTURE = 1;

    //读写权限
    private static String[] PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
            ,Manifest.permission.RECORD_AUDIO};
    //请求状态码
    private static final int REQUEST_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //状态栏隐藏
        getSupportActionBar().hide(); //标题栏隐藏
        setContentView(R.layout.main_activity_view);
        group=findViewById(R.id.radio_group);
        mainRadio=findViewById(R.id.recycle_radio);
        shotRadio=findViewById(R.id.shot_radio);
        albumRadio=findViewById(R.id.show_video_radio);
        aboutMeRadio=findViewById(R.id.about_me_radio);
        addDefaultFragment();

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (checkedId){
                    case R.id.recycle_radio:
                        if (mainFragment==null){
                            mainFragment=new RecyclerFragment();
                        }
                        fragmentTransaction.replace(R.id.content_view,mainFragment);
                        break;
                    case R.id.shot_radio:
                        checkPermission();
                        break;
                    case R.id.show_video_radio:
                        Intent intent=new Intent(group.getContext(), RecyclerLocalActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.about_me_radio:
                        break;
                }
                setRadioState();
                fragmentTransaction.commit();
            }
        });
    }

    public void setRadioState(){
        if (mainRadio.isChecked()){
            mainRadio.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,getDrawable(R.drawable.ic_play_video_on),null,null);
        }else{
            mainRadio.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,getDrawable(R.drawable.ic_play_video_off),null,null);
        }
        if (shotRadio.isChecked()){
            shotRadio.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,getDrawable(R.drawable.ic_shot_video_on),null,null);
        }else{
            shotRadio.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,getDrawable(R.drawable.ic_shot_video_off),null,null);
        }
        if (albumRadio.isChecked()){
            albumRadio.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,getDrawable(R.drawable.ic_album_on),null,null);
        }else{
            albumRadio.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,getDrawable(R.drawable.ic_album_off),null,null);
        }
        if(aboutMeRadio.isChecked()){
            aboutMeRadio.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,getDrawable(R.drawable.ic_about_me_on),null,null);
        }else{
            aboutMeRadio.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,getDrawable(R.drawable.ic_about_me_off),null,null);
        }
    }

    public void addDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RecyclerFragment recyclerFragment=new RecyclerFragment();
        fragmentTransaction.add(R.id.content_view, recyclerFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void dispatchTakeVideoIntent() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    private void checkPermission(){
        boolean isGotPermission=true;
        for(String permission :PERMISSIONS){
            if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                isGotPermission=false;
                break;
            }
        }
        if(isGotPermission){
            dispatchTakeVideoIntent();
        }else{
            requestForPermission();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestForPermission(){
        List<String> p = new ArrayList<>();
        for(String permission :PERMISSIONS){
            if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                p.add(permission);
            }
        }
        if(p.size() > 0){
            requestPermissions(p.toArray(new String[p.size()]),REQUEST_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==REQUEST_PERMISSION_CODE){
            boolean isGotPermission=true;
            for(int result:grantResults){
                if (result==PackageManager.PERMISSION_DENIED){
                    isGotPermission=false;
                    break;
                }
            }
            if(isGotPermission){
                //有权限，调用摄像头
                dispatchTakeVideoIntent();
            }else {
                Toast.makeText(this, "请授权", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
