package com.example.recyclerviewplayer.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import com.example.recyclerviewplayer.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

/** 本地视频通过传递本地视频的uri给这个界面，这个界面调用Videoview来播放视频

    创建人：钟健
    时间：2020.5.28
 **/

public class LocalVideoActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //状态栏隐藏
        getSupportActionBar().hide(); //标题栏隐藏
        setContentView(R.layout.activity_local_video);

        Intent intent = getIntent();
        String str = intent.getStringExtra("videopath");//获取本地视频路径
        Uri uri = Uri.parse(str);

        videoView = findViewById(R.id.V_video);//用Videoview播放视频
       videoView.setVideoURI(uri);

        MediaController mediaController=new MediaController(this); //调用视频的控制器
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();
        videoView.start();
    }
}
