package com.clipplr.platform.controller.clip;

import com.clipplr.platform.common.core.conversion.annotation.Bounds;
import com.clipplr.platform.persistence.mybatis.domain.clip.Clip;
import com.clipplr.platform.persistence.mybatis.domain.clip.ClipBoard;
import com.clipplr.platform.persistence.mybatis.domain.clip.ClipPostRequest;
import com.clipplr.platform.persistence.mybatis.domain.clip.SetClipTagRequest;
import com.clipplr.platform.persistence.service.clip.ClipService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by simon on 10/31/15.
 */
@Validated
@RestController
@RequestMapping(value = "/api/clip", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClipController {

    @Autowired
    ClipService clipService;

    @ApiOperation(value = "List all of the Clips", notes = "전체 클립 리스트를 가져온다")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<HashMap<String, Object>> getClipList(
            @ApiParam(required = false, value = "RowBounds values for pagination (offset, limit).")
            @RequestParam(required = false) @Bounds RowBounds bounds
    ) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("bounds", bounds);

        return clipService.getClipList(params);
    }

    @ApiOperation(value = "Detail Of the Specified Clip", notes = "클립 아이디로 클립의 디테일 정보를 가져온다")
    @RequestMapping(value = "/{ID}/detail", method = RequestMethod.GET)
    @ResponseBody
    public Clip getClipDetail(
            @ApiParam(required = true, value = "clip ID") @PathVariable(value = "ID") Long clipID
    ) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("clipID", clipID);

        return clipService.getClipByID(params);
    }

    @ApiOperation(value = "Save Of the Clip", notes = "클립을 저장한다")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Long saveClip(
            @ApiParam(required = true, value = "Clip") @RequestBody(required = true) ClipPostRequest clip
    ) {
        return clipService.saveClip(clip);
    }

    @ApiOperation(value = "Delete Of the Specified Clip", notes = "클립을 삭제한다")
    @RequestMapping(value = "/{ID}/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteClip(
            @ApiParam(required = true, value = "Clip ID") @PathVariable(value = "ID") Long clipID
    ) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("clipID", clipID);

        clipService.deleteClip(params);
    }

//    @ApiOperation(value = "Modify Of the Specified Clip", notes = "클립을 수정한다")
//    @RequestMapping(value = "/{ID}/modify", method = RequestMethod.PUT)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public void modifyClip(
//            @ApiParam(required = true, value = "Clip") @RequestBody(required = true) @Valid Clip clip
//    ) {
//        clipService.modifyClip(clip);
//    }

    @ApiOperation(value = "Get All of the Clip Tags", notes = "사용할수 있는 클립 태그를 조회한다")
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> getClipTags() {
        return clipService.getClipTags();
    }

    @ApiOperation(value = "Set Tags the Specified Clip", notes = "클립에 태그를 지정한다")
    @RequestMapping(value = "/{ID}/tags", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void setTagsToClip(
            @ApiParam(required = true, value = "Clip ID") @PathVariable(value = "ID") Long clipID,
            @ApiParam(required = true, value = "Clip Tags") @RequestBody(required = true) SetClipTagRequest setClipTagRequest
    ) {
        clipService.setTagsToClip(clipID, setClipTagRequest);
    }

    @ApiOperation(value = "Save Of the Clip", notes = "클립을 클립 보드에 연결한다.")
    @RequestMapping(value = "/board/save", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void saveClipBoard(
            @ApiParam(required = true, value = "Clip") @RequestBody(required = true) ClipBoard clipBoard
    ) {
        clipService.saveClipboard(clipBoard);
    }
}