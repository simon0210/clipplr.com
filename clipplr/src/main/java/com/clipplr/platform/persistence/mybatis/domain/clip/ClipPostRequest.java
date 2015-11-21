package com.clipplr.platform.persistence.mybatis.domain.clip;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by simon on 11/20/15.
 */
public class ClipPostRequest implements Serializable {

    String title;

    String imageUrl;

    ArrayList<ClipPost> clipPosts;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<ClipPost> getClipPosts() {
        return clipPosts;
    }

    public void setClipPosts(ArrayList<ClipPost> clipPosts) {
        this.clipPosts = clipPosts;
    }
}
