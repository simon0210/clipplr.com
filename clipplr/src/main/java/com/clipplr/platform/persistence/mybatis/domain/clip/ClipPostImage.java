package com.clipplr.platform.persistence.mybatis.domain.clip;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import java.io.Serializable;

@AutoProperty
public class ClipPostImage implements Serializable {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long clipPostId;

    private String imageUrl;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClipPostId() {
        return clipPostId;
    }

    public void setClipPostId(Long clipPostId) {
        this.clipPostId = clipPostId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    @Override
    public boolean equals(Object that) {
       return Pojomatic.equals(this, that);
    }

    @Override
    public int hashCode() {
       return Pojomatic.hashCode(this);
    }

    @Override
    public String toString() {
        return Pojomatic.toString(this);
    }
}