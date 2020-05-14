package com.example.recyclerviewplayer.recyclerview;

import android.util.Log;
import android.view.ActionProvider;
import android.view.View;

import com.example.recyclerviewplayer.recyclerview.player.VideoPlayerIJK;
import com.example.recyclerviewplayer.recyclerview.player.VideoPlayerListener;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class ResourcePlayer {
    private VideoPlayerIJK ijkPlayer;
    private String resource;
    private static boolean isPlay;

    public ResourcePlayer(VideoPlayerIJK ijkPlayer, String resource){
        this.ijkPlayer=ijkPlayer;
        this.resource=resource;
        isPlay=true;
        play();

        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ijkPlayer.setListener(new VideoPlayerListener());
        ijkPlayer.getMeasuredWidth();
        ijkPlayer.setVideoPath(resource);

        ijkPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay){
                    pause();
                    isPlay=false;
                }else{
                    play();
                    isPlay=true;
                }
            }
        });
    }

    public void play(){
        ijkPlayer.start();
    }

    public void pause(){
        ijkPlayer.pause();
    }

}
