package com.clipplr.platform.persistence.mybatis.domain.clip;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.URL;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;
import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

@AutoProperty
@ApiModel(value = "Clip")
public class Clip implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonIgnore
    private Long authorId;

    @NotNull(message="메인 타이틀을 입력하세요.")
    private String title;

    @URL
    @NotNull(message="대표 이미지를 설정하세요.")
    private String imageUrl;

    @JsonIgnore
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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

    /* Custom */
    private ClipStatistics clipStatistics;
    private ArrayList<String> clipTags;
    private ArrayList<ClipPost> clipPosts;

    @JsonIgnore
    private String tags;

    public ClipStatistics getClipStatistics() {
        return clipStatistics;
    }

    public void setClipStatistics(ClipStatistics clipStatistics) {
        this.clipStatistics = clipStatistics;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        StringTokenizer st = new StringTokenizer(tags, ",");
        ArrayList<String> categoryTags = new ArrayList<>();

        while(st.hasMoreTokens()) {
            categoryTags.add(st.nextToken());
        }

        this.clipTags = categoryTags;
        this.tags = tags;
    }

    public ArrayList<String> getClipTags() {
        return clipTags;
    }

    public void setClipTags(ArrayList<String> clipTags) {
        this.clipTags = clipTags;
    }

    public ArrayList<ClipPost> getClipPosts() {
        return clipPosts;
    }

    public void setClipPosts(ArrayList<ClipPost> clipPosts) {
        this.clipPosts = clipPosts;
    }
}