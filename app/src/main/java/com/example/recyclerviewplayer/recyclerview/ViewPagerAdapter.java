package com.example.recyclerviewplayer.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recyclerviewplayer.R;

import com.example.recyclerviewplayer.recyclerview.player.VideoPlayerIJK;

/**
 *  viewpager2的适配器
 *
 *  创建人：刘兴贤
 *  最后修改时间：2020.5.28
 */

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder> {

    private static final String TEG="LXX";

    private int mNumberItems;

    private static int viewHolderCount;

    private final int initPosition;

    private ResourceParser parser;

    public ViewPagerAdapter(int initPos){
        viewHolderCount=0;
        initPosition=initPos;
        parser=new ResourceParser();
        mNumberItems=parser.size();
    }

    @NonNull
    @Override
    public PagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        int layout_id=R.layout.viewpager_item;
        LayoutInflater inflater=LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view=inflater.inflate(layout_id,parent,shouldAttachToParentImmediately);
        PagerViewHolder holder=new PagerViewHolder(view);
        viewHolderCount++;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PagerViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    @Override
    public void onViewAttachedToWindow(@NonNull PagerViewHolder holder) {
        //页面出现时开始播放
        super.onViewAttachedToWindow(holder);
        holder.ijkPlayer.start();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull PagerViewHolder holder) {
        //页面消失时停止播放
        super.onViewDetachedFromWindow(holder);
        holder.ijkPlayer.pause();
    }

    public class PagerViewHolder extends RecyclerView.ViewHolder{
        private VideoPlayerIJK ijkPlayer;
        private ResourcePlayer resourcePlayer;

        public PagerViewHolder(@NonNull View itemView) {
            super(itemView);
            ijkPlayer= itemView.findViewById(R.id.ijk_player_view);

        }
        public void bind(int position){
            //使用pos加上初始点进来的pos得到在列表中的序号
//            int currentPos=position+initPosition;
            int currentPos=(position+initPosition)%mNumberItems;
            String resourceURI=parser.getFeedurl(currentPos);
            Log.d(TEG, resourceURI);
            resourcePlayer=new ResourcePlayer(ijkPlayer, resourceURI);
        }
    }
}
