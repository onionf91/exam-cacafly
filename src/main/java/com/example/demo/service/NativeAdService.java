package com.example.demo.service;

import groovy.json.JsonSlurper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class NativeAdService {

    public void dumpAdSourceUrl() {
        log.info("fetch ad info from : " + AD_SOURCE_URL);
    }

    public void fetchAd() {
        ad = restTemplate.getForObject(AD_SOURCE_URL, String.class);
        log.info("ad message : " + ad);
    }

    public void parseAd() {
        JsonSlurper parser = new JsonSlurper();
        Map obj = (Map)parser.parse(ad.getBytes());
        Map nativeObj = (Map)obj.get("native");
        parseClickUrl(nativeObj);
        parseImpressionLink(nativeObj);
        List<Map> assetsObj = (List<Map>)nativeObj.get("assets");
        for (Map asset : assetsObj) {
            parseTitle(asset);
            parseDescription(asset);
            parseImage(asset);
            parseClickUrl(asset);
        }
    }

    private void parseTitle(Map asset) {
        Map obj = (Map)asset.get("title");
        if (obj != null) {
            String title = (String)obj.get("text");
            log.info("title:" + title);
        }
    }

    private void parseDescription(Map asset) {
        Map obj = (Map)asset.get("data");
        if (obj != null) {
            String description = (String)obj.get("value");
            log.info("description:" + description);
        }
    }

    private void parseImage(Map asset) {
        Map obj = (Map)asset.get("img");
        if (obj != null) {
            String url = (String)obj.get("url");
            log.info("img url:" + url);
        }
    }

    private void parseImpressionLink(Map asset) {
        List<String> obj = (List<String>)asset.get("impressionEvent");
        if (obj != null) {
            log.info("impression links:");
            for (String link : obj) {
                log.info(link);
            }
        }
    }

    private void parseClickUrl(Map asset) {
        Map obj = (Map)asset.get("link");
        if (obj != null) {
            String url = (String)obj.get("url");
            log.info("click url:" + url);
        }
    }

    private String ad;

    @Value("${ad.source.url}")
    private String AD_SOURCE_URL;

    @Autowired
    private RestTemplate restTemplate;
}
