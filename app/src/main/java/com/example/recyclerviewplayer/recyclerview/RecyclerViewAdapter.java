package com.example.recyclerviewplayer.recyclerview;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recyclerviewplayer.R;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * 网络视频recyclerview的适配器，用来处理单个视频的显示状况
 * 创建人：钟健
 * 时间：2020.5.28
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NumberViewHolder> {

    private static final String TAG = "LXX";

    private int mNumberItems;

    private final ListItemClickListener mOnClickListener;

    private static int viewHolderCount;

    private Context context;

    private ResourceParser parser;

    public RecyclerViewAdapter(Context context, int numListItems, ListItemClickListener listener) {
        mNumberItems = numListItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
        this.context = context;
        parser=new ResourceParser();
        Log.d("h","h");
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recycler_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        viewHolderCount++;
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder numberViewHolder, final int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);
        try {
            numberViewHolder.bind(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        numberViewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){ //设置点击事件：点击封面图片跳转到播放页面
                Intent intent=new Intent(context, ViewPagerActivity.class);

                intent.putExtra("initPos",position);//传递第几个view的视频地址信息

                context.startActivity(intent);//跳转到播放界面
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView author_text;
        private final TextView time_text;
        private final ImageView imageView;


        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            author_text = (TextView)itemView.findViewById(R.id.author_text);
            time_text = (TextView)itemView.findViewById(R.id.time_text);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        public void bind(int position) throws JSONException {
            author_text.setText("昵称:"+parser.getNickname(position));
            time_text.setText("描述"+parser.getDescription(position));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Log.d("LXX",parser.getFeedurl(position));
            Glide.with(imageView.getContext()) //使用Glide加载封面图
                    .setDefaultRequestOptions(
                          new RequestOptions()
                                  .frame(1000000)
                                  .centerCrop()
                    )
                    .load(parser.getFeedurl(position))
                    .into(imageView);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}
