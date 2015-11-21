package com.clipplr.platform.persistence.mybatis.domain.clip;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by simon on 11/16/15.
 */
@AutoProperty
public class SetClipTagRequest implements Serializable {

    private static final long serialVersionUID = -3533394552592259949L;

    ArrayList<String> tags;

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
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
