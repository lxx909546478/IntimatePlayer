package com.example.recyclerviewplayer.recyclerview;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

/**
 *  接口类get方法
 *
 *  创建人：刘兴贤
 *  最后修改时间：2020.5.30
 */

public interface ApiService {
    @GET("api/invoke/video/invoke/video")
    Call<List<URIResponse>> getURI();
}
