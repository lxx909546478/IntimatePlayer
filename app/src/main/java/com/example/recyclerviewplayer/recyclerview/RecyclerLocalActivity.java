package com.example.recyclerviewplayer.recyclerview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import com.example.recyclerviewplayer.R;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
    播放本地视频recyclerview主页面，定义了create方法，滚动事件的方法，遍历本地录像视频文件夹的所有视频内容信息传给Adapter，
    并在此处申请了内存读写权限

    创建人：钟健
    时间：2020.5.28

 **/

public class RecyclerLocalActivity extends AppCompatActivity implements RecyclerLocalAdapter.ListItemClickListener{

    private static final String TAG = "zhongjian";

    private RecyclerLocalAdapter mAdapter; //recyclerview的适应器

    private RecyclerView mNumbersListView; //整个recyclerview页面对象

    private static int NUM_LIST_ITEMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        judgePermission();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //状态栏隐藏
        getSupportActionBar().hide(); //标题栏隐藏
        setContentView(R.layout.activity_recycler_local);

        mNumbersListView = findViewById(R.id.RecyclerviewList); //设置recyclerview页面对象的布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mNumbersListView.setLayoutManager(layoutManager);
        mNumbersListView.setHasFixedSize(true);

        List<String> fileNameList = new ArrayList<>();//遍历本地视频文件的文件夹
        String cameraPath = "/storage/self/primary/DCIM/Camera/"; //视频文件的地址
        File file = new File(cameraPath);
        File[] Subfile = file.listFiles();
        String[] videoTail={".mp4",".avi",".wmv",".3gp",".mkv",".flv",".rmvb"}; //过滤掉图片文件保留视频文件
        for(int iFileLength = 0;iFileLength < Subfile.length;iFileLength++){
            if(!Subfile[iFileLength].isDirectory()){
                boolean isVideo=false;
                String fileName=Subfile[iFileLength].getName();
                for (String tail:videoTail){
                    if (fileName.contains(tail)){
                        isVideo=true;
                        break;
                    }
                }
                if (isVideo){
                    fileNameList.add(cameraPath+Subfile[iFileLength].getName());
                }
            }
        }
        NUM_LIST_ITEMS = fileNameList.size();


        mAdapter = new RecyclerLocalAdapter(RecyclerLocalActivity.this,NUM_LIST_ITEMS, this,fileNameList);//将必要信息传给适应器

        mNumbersListView.setAdapter(mAdapter);
        mNumbersListView.addOnScrollListener(new RecyclerView.OnScrollListener() { //滑动事件处理

            // 最后一个完全可见项的位置
            private int lastCompletelyVisibleItemPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                //滚动事件
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) { //滑动到底部跳出提示
                    if (visibleItemCount > 0 && lastCompletelyVisibleItemPosition >= totalItemCount - 1) {
                        Toast.makeText(RecyclerLocalActivity.this, "已滑动到底部!,触发loadMore", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) { //滚动处理
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                }
                Log.d(TAG, "onScrolled: lastVisiblePosition=" + lastCompletelyVisibleItemPosition);
            }
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }

    protected void judgePermission() { //获取读写权限

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查该权限是否已经获取
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝

            // sd卡权限
            String[] SdCardPermission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (ContextCompat.checkSelfPermission(this, SdCardPermission[0]) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, SdCardPermission, 100);
            }

            //手机状态权限
            String[] readPhoneStatePermission = {Manifest.permission.READ_PHONE_STATE};
            if (ContextCompat.checkSelfPermission(this, readPhoneStatePermission[0]) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, readPhoneStatePermission, 200);
            }

            //定位权限
            String[] locationPermission = {Manifest.permission.ACCESS_FINE_LOCATION};
            if (ContextCompat.checkSelfPermission(this, locationPermission[0]) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, locationPermission, 300);
            }

            String[] ACCESS_COARSE_LOCATION = {Manifest.permission.ACCESS_COARSE_LOCATION};
            if (ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION[0]) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, ACCESS_COARSE_LOCATION, 400);
            }


            String[] READ_EXTERNAL_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE};
            if (ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE[0]) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, READ_EXTERNAL_STORAGE, 500);
            }

            String[] WRITE_EXTERNAL_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE[0]) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, WRITE_EXTERNAL_STORAGE, 600);
            }

        }else{
            //doSdCardResult();
        }
        //LocationClient.reStart();
    }
}
