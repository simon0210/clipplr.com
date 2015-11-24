package com.clipplr.platform.persistence.service.clip;

import com.clipplr.platform.persistence.mybatis.domain.clip.Clip;
import com.clipplr.platform.persistence.mybatis.domain.clip.ClipBoard;
import com.clipplr.platform.persistence.mybatis.domain.clip.ClipPostRequest;
import com.clipplr.platform.persistence.mybatis.domain.clip.SetClipTagRequest;

import java.util.HashMap;
import java.util.List;

/**
 * Created by simon on 11/6/15.
 */
public interface ClipService {

    List<HashMap<String, Object>> getClipList(HashMap<String, Object> params);

    Clip getClipByID(HashMap<String, Object> params);

    Long saveClip(ClipPostRequest clip);

    void deleteClip(HashMap<String, Object> params);

    void modifyClip(Clip clip);

    HashMap<String, Object> getClipTags();

    void setTagsToClip(Long clipID, SetClipTagRequest setClipTagRequest);

    void saveClipboard(ClipBoard clipBoard);
}
