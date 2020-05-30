package com.example.recyclerviewplayer.recyclerview;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  资源解析器
 *
 *  创建人：刘兴贤
 *  最后修改时间：2020.5.28
 */

public class ResourceParser {

    //资源结构
    //id（String）：标识
    //feedurl（String）：视频资源URI
    //nickname（String）：视频发布者的昵称
    //description（String）:视频描述
    //likecount(Integer)：喜欢该视频的人数
    //avatar（String）:用户头像URI
    private static final String TEG="LXX";

    //存储资源列表
    private static List<URIResponse> resourceList;

    public ResourceParser(){

    }

    public ResourceParser(int first){
        getResource();
    }

    private void getResource(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getURI().enqueue(new Callback<List<URIResponse>>() {
            @Override
            public void onResponse(Call<List<URIResponse>> call, Response<List<URIResponse>> response) {
                if (response.body() != null) {
                    resourceList = response.body();
                    for (URIResponse uri:resourceList){
                        Log.d("retrofit", uri.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<URIResponse>> call, Throwable t) {
                Log.d("retrofit", t.getMessage());
            }
        });
    }

    public int size(){
        return resourceList.size();
    }

    public String getFeedurl(int pos){
        return resourceList.get(pos).getFeedurl();
    }

    public String getDescription(int pos){
        return resourceList.get(pos).getDescription();
    }

    public String getNickname(int pos){
        return resourceList.get(pos).getNickname();
    }

    public int getLikecount(int pos) {
        return resourceList.get(pos).getLikecount();
    }
}
