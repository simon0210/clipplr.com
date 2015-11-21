package com.clipplr.platform.persistence.mybatis.domain.clip;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import java.io.Serializable;

@AutoProperty
public class UserDefineTag implements Serializable {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long clipId;

    private String tagName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClipId() {
        return clipId;
    }

    public void setClipId(Long clipId) {
        this.clipId = clipId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
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