package com.clipplr.platform.persistence.service.crawler;

import com.clipplr.platform.persistence.mybatis.domain.crawler.CrawlingResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

/**
 * Created by simon on 11/11/15.
 */
@Service
public class CrawlingServiceImpl implements CrawlingService {

    private final Logger logger = LoggerFactory.getLogger(CrawlingServiceImpl.class);
    private static final int ACCEPT_IMAGE_CNTS = 20;

    @Override
    public CrawlingResult crawlingURL(String url) {
        return scrap(url);
    }

    private CrawlingResult scrap(String url) {

        Document document = null;

        try {
            document = Jsoup.connect(url).userAgent("Mozilla").get();
        } catch (IOException ex) {
            logger.error(ex.toString()); //TO_DO: Handling Exception
        }

        CrawlingResult result = new CrawlingResult();
        return parseDocument(document, url, result);
    }

    private CrawlingResult parseDocument(Document document, String url, CrawlingResult result) {

        Elements ogTitle = document.select("meta[property=og:title]");

        //Select Title
        if(ogTitle.size() == 0) {
            Elements twitterTitle = document.select("meta[property=og:title]");

            if(twitterTitle.size() == 0) {
                result.setTitle(document.title());
            } else {
                result.setTitle(twitterTitle.attr("content"));
            }
        } else {
            result.setTitle(ogTitle.attr("content"));
        }

        Elements ogDescription = document.select("meta[property=og:description]");

        //Select Description
        if(ogDescription.size() == 0) {
            Elements twitterDescription = document.select("meta[property=twitter:description]");

            if(twitterDescription.size() == 0) {
                Elements mainDescription = document.select("meta[name=description]");
                if(twitterDescription.size() > 0)
                    result.setDescription(mainDescription.attr("content"));
            } else {
                result.setDescription(twitterDescription.attr("content"));
            }
        } else {
            result.setDescription(ogDescription.attr("content"));
        }

        //Select First Image
        ArrayList<String> imgUrls = new ArrayList<>();

        Elements ogImage = document.select("meta[property=og:image]");

        if (ogImage.size() == 0) {
            Elements twitterImage = document.select("meta[property=twitter:image]");

            if (twitterImage.size() == 0) {
                Elements faviconElement = document.head().select("link[href~=.*\\.(ico|png)]");
                if (faviconElement.size() > 0) {
                    String faviconUrl = faviconElement.first().attr("href");
                    if (faviconUrl.startsWith("/")) {
                        faviconUrl = url + faviconUrl;
                    }
                    imgUrls.add(faviconUrl);
                }
            } else {
                imgUrls.add(twitterImage.attr("content"));
            }
        } else {
            imgUrls.add(ogImage.attr("content"));
        }

        //Get img:src
        Elements elementImageSource = document.select("img[src]");

        int loopcnt = 0;
        for (Element e : elementImageSource){
            if(loopcnt >= ACCEPT_IMAGE_CNTS) {
                break;
            }
            imgUrls.add(e.attr("abs:src"));
            loopcnt++;
        }

        result.setImgUrls(imgUrls);
        result.setImageCnt(imgUrls.size());
//
//        //Get a:href
//        Elements elementLinkSource = document.select("a[href]");
//
//        for (Element e : elementLinkSource){
//            String src = e.attr("abs:href");
//
//            if(src != "")
//                aLinks.add(src);
//        }
//
//        result.setaLinks(aLinks);
//

        URL urlAddress = null;

        try {
            urlAddress = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        result.setUrl(url);
        result.setHost(urlAddress.getHost());

        if(imgUrls.size() < ACCEPT_IMAGE_CNTS) {
            return parseIframeImage(document, result);
        }

        return result;
    }

    private CrawlingResult parseIframeImage(org.jsoup.nodes.Document document, CrawlingResult result) {
        ArrayList<String> iFrameSrc = new ArrayList<>();
        ArrayList<String> resultImages = new ArrayList<>();

        Elements elementImageSource = document.select("frame[src]");

        if (elementImageSource == null) {
            return result;
        }

        for (Element e : elementImageSource){
            iFrameSrc.add(e.attr("abs:src"));
        }

        if (iFrameSrc.size() > 0) {

            for (Element e1 : elementImageSource){
                org.jsoup.nodes.Document docFrameSrc = null;
                String src = e1.attr("abs:src");

                try{
                    docFrameSrc = Jsoup.connect(src).get();
                }catch (IOException ex) {
                    logger.error(ex.toString()); //TO_DO: Handling Excpetion
                }

                Elements frameElementImageSource = docFrameSrc.select("img[src]");

                for (Element e2 : frameElementImageSource){
                    resultImages = result.getImgUrls();
                    resultImages.add(e2.attr("abs:src"));

                    if(resultImages.size() >= ACCEPT_IMAGE_CNTS) {
                        return result;
                    }
                }
            }

            result.setImgUrls(resultImages);
        }

        return result;
    }
}
