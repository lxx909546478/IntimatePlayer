package com.example.recyclerviewplayer.recyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewplayer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFragment extends Fragment implements RecyclerViewAdapter.ListItemClickListener {
    private static final String TAG = "zhongjian";
    private RecyclerViewAdapter mAdapter;
    private RecyclerView mNumbersListView;

    private static final int NUM_LIST_ITEMS = 10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View settingLayout = inflater.inflate(R.layout.activity_recycler_view, container, false);
        mNumbersListView = settingLayout.findViewById(R.id.RecyclerviewList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(settingLayout.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mNumbersListView.setLayoutManager(layoutManager);
        mNumbersListView.setHasFixedSize(true);
        List<JSONObject> jsonList = new ArrayList<>(); //处理JSON数组
        String str = "[{\"_id\":\"5e9830b0ce330a0248e89d86\",\"feedurl\":\"http://jzvd.nathen.cn/video/1137e480-170bac9c523-0007-1823-c86-de200.mp4\",\"nickname\":\"王火火\",\"description\":\"这是第一条Feed数据\",\"likecount\":10000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/f402a0e012b14d41ad07939746844c5e00005.jpg\"},{\"_id\":\"5e9833dec47d14020e85f416\",\"feedurl\":\"http://jzvd.nathen.cn/video/e0bd348-170bac9c3b8-0007-1823-c86-de200.mp4\",\"nickname\":\"LILILI\",\"description\":\"这是一条一起学猫叫的视频\",\"likecount\":120000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/8bd6d06878fc4676a62290cbe8b5511f00005.jpg\"},{\"_id\":\"5e9833e0c47d14020e85f418\",\"feedurl\":\"http://jzvd.nathen.cn/video/2f03c005-170bac9abac-0007-1823-c86-de200.mp4\",\"nickname\":\"新闻启示录\",\"description\":\"赶紧把这个转发给你们的女朋友吧，这才是对她们最负责的AI\",\"likecount\":45000000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/371ddcdf7bbe46b682913f3d3353192000005.jpg\"},{\"_id\":\"5e9833e0a21527020d426e91\",\"feedurl\":\"http://jzvd.nathen.cn/video/7bf938c-170bac9c18a-0007-1823-c86-de200.mp4\",\"nickname\":\"综艺大咖秀\",\"description\":\"男明星身高暴击\",\"likecount\":98777000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/dabe6ca3c71942fd926a86c8996d750f00005.jpg\"},{\"_id\":\"5e9833e1c47d14020e85f43a\",\"feedurl\":\"http://jzvd.nathen.cn/video/47788f38-170bac9ab8a-0007-1823-c86-de200.mp4\",\"nickname\":\"南翔不爱吃饭\",\"description\":\"挑战手抓饼的一百种吃法第七天\",\"likecount\":500000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/edac56544e2f43bb827bd0e819db381000005.jpg\"},{\"_id\":\"5e983406a21527020d426f1f\",\"feedurl\":\"http://jzvd.nathen.cn/video/2d6ffe8f-170bac9ab87-0007-1823-c86-de200.mp4\",\"nickname\":\"王者主播那些事儿\",\"description\":\"你有试过蔡文姬打野吗？\",\"likecount\":1000000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/531f1e488eb84b898ae9ca7f6ba758ed00005.jpg\"},{\"_id\":\"5e98340da21527020d426f43\",\"feedurl\":\"http://jzvd.nathen.cn/video/633e0ce-170bac9ab65-0007-1823-c86-de200.mp4\",\"nickname\":\"十秒学做菜\",\"description\":\"两款爱吃的三明治分享\",\"likecount\":1010102,\"avatar\":\"http://jzvd.nathen.cn/snapshot/ad0331e78393457d88ded2257d9e47c800005.jpg\"},{\"_id\":\"5e983415a21527020d426f7b\",\"feedurl\":\"http://jzvd.nathen.cn/video/2d6ffe8f-170bac9ab87-0007-1823-c86-de200.mp4\",\"nickname\":\"九零后老母亲\",\"description\":\"从孕期到产后，老公一直要我用这个勺子喝汤\",\"likecount\":94321,\"avatar\":\"http://jzvd.nathen.cn/snapshot/6ae53110f7fd470683587746f027698400005.jpg\"},{\"_id\":\"5e98341da21527020d426f97\",\"feedurl\":\"http://jzvd.nathen.cn/video/51f7552c-170bac98718-0007-1823-c86-de200.mp4\",\"nickname\":\"FPX电子竞技俱乐部\",\"description\":\"甲方的需求：F P X冠军皮肤的起源\",\"likecount\":1200000,\"avatar\":\"http://jzvd.nathen.cn/snapshot/ef384b95897b470c80a4aca4dd1112a500005.jpg\"},{\"_id\":\"5e983423a21527020d426fbb\",\"feedurl\":\"http://jzvd.nathen.cn/video/2a101070-170bad88892-0007-1823-c86-de200.mp4\",\"nickname\":\"抖音官方广告报名！\",\"description\":\"买它！买它！买它！\",\"likecount\":480,\"avatar\":\"http://jzvd.nathen.cn/snapshot/86a055d08b514c9ca1e76e76862105ec00005.jpg\"}]";
        try {
            JSONArray messages = new JSONArray(str); // 首先把字符串转成 JSONArray对象
            for(int i=0;i < messages.length();i++)
                jsonList.add(messages.getJSONObject(i));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mAdapter = new RecyclerViewAdapter(settingLayout.getContext(),NUM_LIST_ITEMS, this,jsonList);

        mNumbersListView.setAdapter(mAdapter);
        mNumbersListView.addOnScrollListener(new RecyclerView.OnScrollListener() { //滑动事件

            // 最后一个完全可见项的位置
            private int lastCompletelyVisibleItemPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                //滚动事件
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (visibleItemCount > 0 && lastCompletelyVisibleItemPosition >= totalItemCount - 1) {
                        Toast.makeText(settingLayout.getContext(), "已经到底了哦", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                }
                Log.d(TAG, "onScrolled: lastVisiblePosition=" + lastCompletelyVisibleItemPosition);
            }
        });
        return settingLayout;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}
