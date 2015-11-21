package com.clipplr.platform.persistence.mybatis.domain.crawler;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by simon on 11/11/15.
 */
public class CrawlingResult implements Serializable {
    private static final long serialVersionUID = 4291044210878930956L;

    String url;
    String host;
    String title;
    String description;

    ArrayList<String> imgUrls;

    @JsonIgnore
    ArrayList<String> aLinks;
    @JsonIgnore
    ArrayList<String> iFrameSrc;
    @JsonIgnore
    int imageCnt;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(ArrayList<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public int getImageCnt() {
        return imageCnt;
    }

    public void setImageCnt(int imageCnt) {
        this.imageCnt = imageCnt;
    }

    public ArrayList<String> getaLinks() {
        return aLinks;
    }

    public void setaLinks(ArrayList<String> aLinks) {
        this.aLinks = aLinks;
    }

    public ArrayList<String> getiFrameSrc() {
        return iFrameSrc;
    }

    public void setiFrameSrcs(ArrayList<String> iFrameSrc) {
        this.iFrameSrc = iFrameSrc;
    }
}
