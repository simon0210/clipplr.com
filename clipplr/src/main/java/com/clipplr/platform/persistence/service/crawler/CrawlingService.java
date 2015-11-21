package com.clipplr.platform.persistence.service.crawler;

import com.clipplr.platform.persistence.mybatis.domain.crawler.CrawlingResult;

import java.io.IOException;

/**
 * Created by simon on 11/11/15.
 */
public interface CrawlingService {
    CrawlingResult crawlingURL(String url) throws IOException;
}
