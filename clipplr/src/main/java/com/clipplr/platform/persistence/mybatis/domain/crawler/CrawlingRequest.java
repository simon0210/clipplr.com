package com.clipplr.platform.persistence.mybatis.domain.crawler;

import java.io.Serializable;

/**
 * Created by simon on 11/11/15.
 */
public class CrawlingRequest implements Serializable {
    private static final long serialVersionUID = 4291044210878930957L;

    String url;

    int imageCnt;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImageCnt() {
        return imageCnt;
    }

    public void setImageCnt(int imageCnt) {
        this.imageCnt = imageCnt;
    }
}
