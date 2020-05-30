package com.example.recyclerviewplayer.recyclerview;

import com.google.gson.annotations.SerializedName;

/**
 *  API响应类格式
 *
 *  创建人：刘兴贤
 *  最后修改时间：2020.5.28
 */

public class URIResponse {
    @SerializedName("_id")
    public String _id;
    @SerializedName("feedurl")
    public String feedurl;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("description")
    public String description;
    @SerializedName("likecount")
    public int likecount;
    @SerializedName("avatar")
    public String avatar;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFeedurl() {
        return feedurl;
    }

    public void setFeedurl(String feedurl) {
        this.feedurl = feedurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikecount() {
        return likecount;
    }

    public void setLikecount(int likecount) {
        this.likecount = likecount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }



    @Override
    public String toString() {
        return "_id:"+_id+"\n"
                +"feedurl:"+feedurl+"\n"
                +"nickname:"+nickname+"\n"
                +"description:"+description+"\n"
                +"likecount:"+likecount+"\n";
    }
}
