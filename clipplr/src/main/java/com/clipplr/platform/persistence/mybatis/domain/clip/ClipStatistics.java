package com.clipplr.platform.persistence.mybatis.domain.clip;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class ClipStatistics implements Serializable {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long clipId;

    private Long viewCount;

    private Long clipCount;

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

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getClipCount() {
        return clipCount;
    }

    public void setClipCount(Long clipCount) {
        this.clipCount = clipCount;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ClipStatistics other = (ClipStatistics) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClipId() == null ? other.getClipId() == null : this.getClipId().equals(other.getClipId()))
            && (this.getViewCount() == null ? other.getViewCount() == null : this.getViewCount().equals(other.getViewCount()))
            && (this.getClipCount() == null ? other.getClipCount() == null : this.getClipCount().equals(other.getClipCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClipId() == null) ? 0 : getClipId().hashCode());
        result = prime * result + ((getViewCount() == null) ? 0 : getViewCount().hashCode());
        result = prime * result + ((getClipCount() == null) ? 0 : getClipCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", clipId=").append(clipId);
        sb.append(", viewCount=").append(viewCount);
        sb.append(", clipCount=").append(clipCount);
        sb.append("]");
        return sb.toString();
    }
}