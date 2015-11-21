package com.clipplr.platform.controller.crawler;

import com.clipplr.platform.persistence.mybatis.domain.crawler.CrawlingResult;
import com.clipplr.platform.persistence.service.crawler.CrawlingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by simon on 11/11/15.
 */
@RestController
@RequestMapping(value = "/api/crawler", produces = MediaType.APPLICATION_JSON_VALUE)
public class CrawlingController {

    @Autowired
    CrawlingService crawlingService;

    @ApiOperation(value = "Crawling Specified URL", notes = "특정 URL을 크롤링 한다")
    @RequestMapping(value = "/crawlingUrl", method = RequestMethod.GET)
    @ResponseBody
    public CrawlingResult crawlingURL(@RequestParam String url) throws IOException {
        return crawlingService.crawlingURL(url);
    }
}
