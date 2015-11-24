package com.clipplr.platform.persistence.service.clip;

import com.clipplr.platform.exception.clip.ContentDoesNotExist;
import com.clipplr.platform.persistence.mybatis.domain.clip.*;
import com.clipplr.platform.persistence.mybatis.mapper.clip.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by simon on 11/10/15.
 */
@Service
public class ClipServiceImpl implements ClipService {

    @Autowired
    ClipMapper clipRepository;

    @Autowired
    UserDefineTagMapper tagRepository;

    @Autowired
    ClipPostMapper clipPostRepository;

    @Autowired
    ClipStatisticsMapper clipStatisticsRepository;

    @Autowired
    ClipTagMapper clipTagRepository;

    @Autowired
    ClipPostImageMapper postImageRepository;

    @Autowired
    ClipBoardMapper clipBoardRepository;

    @Override
    public List<HashMap<String, Object>> getClipList(HashMap<String, Object> params) {

        List<HashMap<String, Object>> result = new ArrayList<>();

        int total_cnt = clipRepository.countyByselectedClips();
        List<Clip> clipList = clipRepository.selectClips(params);

        result.add(new HashMap<String, Object>(){
            private static final long serialVersionUID = 2705816643563900474L;
            {
                put("total_cnt", total_cnt);
            }
            {
                put("clips", clipList);
            }
        });

        return result;
    }

    @Override
    public Clip getClipByID(HashMap<String, Object> params) {

        List<Clip> clips = clipRepository.selectClips(params);

        ClipPostExample example = new ClipPostExample();
        example.createCriteria().andClipIdEqualTo((Long) params.get("clipID"));
        List<ClipPost> posts = clipPostRepository.selectByExample(example);

        if (clips.size() < 1) {
            throw new ContentDoesNotExist((Long) params.get("clipID"));
        }

        Clip clip = clips.get(0);
        clip.setClipPosts((ArrayList<ClipPost>) posts);

        return clip;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public Long saveClip(ClipPostRequest clip) {
        //insert main clip
        Clip mainClip = new Clip();
        mainClip.setImageUrl(clip.getImageUrl());
        mainClip.setTitle(clip.getTitle());

        clipRepository.insertSelective(mainClip);

        //inset clip posts
        List<ClipPost> clipPosts = clip.getClipPosts();
        for(ClipPost post : clipPosts) {

            //insert clip statistics
            ClipStatistics statistics = new ClipStatistics();
            statistics.setClipCount(0L);
            statistics.setViewCount(0L);
            statistics.setClipId(mainClip.getId());
            clipStatisticsRepository.insert(statistics);

            post.setClipId(mainClip.getId());
            clipPostRepository.insert(post);

            //insert clip post images
            if(post.getImages() != null) {
                for (int i = 0; i < post.getImages().length; i++) {
                    ClipPostImage postImage = new ClipPostImage();
                    postImage.setClipPostId(post.getId());
                    postImage.setImageUrl(post.getImages()[i]);

                    postImageRepository.insertSelective(postImage);
                }
            }
        }

        return mainClip.getId();
    }

    @Override
    public void deleteClip(HashMap<String, Object> params) {
        Clip clip = new Clip();
        clip.setId((Long) params.get("clipID"));
        clip.setIsDeleted(true);

        ClipExample example = new ClipExample();
        example.createCriteria().andIdEqualTo(clip.getId());

        clipRepository.updateByExampleSelective(clip, example);
    }

    @Override
    public void modifyClip(Clip clip) {
        List<ClipPost> clipPosts = clip.getClipPosts();

        for(ClipPost post : clipPosts) {
            clipPostRepository.updateByPrimaryKeySelective(post);
        }

        clipRepository.updateByPrimaryKeySelective(clip);
    }

    @Override
    public HashMap<String, Object> getClipTags() {
        return clipTagRepository.selectTagName();
    }

    @Override
    public void setTagsToClip(Long clipID, SetClipTagRequest setClipTagRequest) {
        List<String> tags = setClipTagRequest.getTags();

        UserDefineTag userDefineTag = new UserDefineTag();
        userDefineTag.setClipId(clipID);

        for(String tag : tags) {
            userDefineTag.setTagName(tag);
            tagRepository.insert(userDefineTag);
        }
    }

    @Override
    public void saveClipboard(ClipBoard clipBoard) {
        ClipBoard board = new ClipBoard();
        board.setClipId(clipBoard.getClipId());
        board.setBoardName(clipBoard.getBoardName());

        String[] tags = clipBoard.getTags();

        //insert clip tags
        if (tags != null) {
            for (int i = 0; i < tags.length; i++) {
                UserDefineTag tag = new UserDefineTag();
                tag.setClipId(clipBoard.getClipId());
                tag.setTagName(tags[i]);

                tagRepository.insert(tag);
            }
        }

        //insert clip board
        clipBoardRepository.insert(board);
    }
}
