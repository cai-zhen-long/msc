package com.offcn.msg;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2019/10/11.
 */
public class New {
    public String  appId;//app_id
    public String  title;//title
    public String  intro;//intro
    public String  source;//source
    public String  url; //url
    public String  updateTime ;//update_time

    public New(String appId, String title, String intro, String source, String url, String updateTime) {
        this.appId = appId;
        this.title = title;
        this.intro = intro;
        this.source = source;
        this.url = url;
        this.updateTime = updateTime;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JSONField(name="update_time",format = "yyyy-MM-dd HH:mm:ss")
    public String getUpdateTime() {
        return updateTime;
    }

    @JSONField(name="update_time",format = "yyyy-MM-dd HH:mm:ss")
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "New{" +
                "appId='" + appId + '\'' +
                ", title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", source='" + source + '\'' +
                ", url='" + url + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
