package com.example.demo.service;

import com.example.demo.domain.*;
import com.example.demo.repository.*;
import groovy.json.JsonSlurper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
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
        if (nativeObj == null) return;
        nativeAd = null;
        List<Map> assetsObj = (List<Map>)nativeObj.get("assets");
        assetsObj.stream().filter(asset -> asset.get("title") != null).findAny().ifPresent( asset -> {
            nativeAd = new NativeAd();
            parseTitle(asset);
        });
        if (nativeAd == null) return;
        parseClickUrl(nativeObj);
        parseImpressionLink(nativeObj);
        for (Map asset : assetsObj) {
            parseDescription(asset);
            parseImage(asset);
            parseClickUrl(asset);
        }

//        log.info("native ad size : " + nativeAdRepo.findAll().size());
//        log.info("click url size : " + clickUrlRepo.findAll().size());
//        log.info("image url size : " + imageUrlRepo.findAll().size());
//        log.info("impression link size : " + impressionLinkRepo.findAll().size());
//        log.info("description size : " + descriptionRepo.findAll().size());
    }

    private void parseTitle(Map asset) {
        Map obj = (Map)asset.get("title");
        if (obj != null) {
            String title = (String)obj.get("text");
            nativeAd.setTitle(toHex(title));
            try {
                nativeAdRepo.save(nativeAd);
                log.info("title:" + toHex(title));
            } catch (Exception e) {
                nativeAd = null;
            }
        }
    }

    private String toHex(String arg) {
        return String.format("%x", new BigInteger(1, arg.getBytes()));
    }

    private void parseDescription(Map asset) {
        Map obj = (Map)asset.get("data");
        if (obj != null) {
            String text = (String)obj.get("value");
//            log.info("description:" + text);
            Description description = new Description();
            description.setNativeAd(nativeAd);
            description.setText(text);
            descriptionRepo.save(description);
        }
    }

    private void parseImage(Map asset) {
        Map obj = (Map)asset.get("img");
        if (obj != null) {
            String url = (String)obj.get("url");
//            log.info("img url:" + url);
            ImageUrl imageUrl = new ImageUrl();
            imageUrl.setNativeAd(nativeAd);
            imageUrl.setUrl(url);
            imageUrlRepo.save(imageUrl);
        }
    }

    private void parseImpressionLink(Map asset) {
        List<String> obj = (List<String>)asset.get("impressionEvent");
        if (obj != null) {
//            log.info("impression links:");
            for (String link : obj) {
//                log.info(link);
                ImpressionLink impressionLink = new ImpressionLink();
                impressionLink.setNativeAd(nativeAd);
                impressionLink.setLink(link);
                impressionLinkRepo.save(impressionLink);
            }
        }
    }

    private void parseClickUrl(Map asset) {
        Map obj = (Map)asset.get("link");
        if (obj != null) {
            String url = (String)obj.get("url");
//            log.info("click url:" + url);
            ClickUrl clickUrl = new ClickUrl();
            clickUrl.setNativeAd(nativeAd);
            clickUrl.setUrl(url);
            clickUrlRepo.save(clickUrl);
        }
    }

    private String ad;
    private NativeAd nativeAd;

    @Value("${ad.source.url}")
    private String AD_SOURCE_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClickUrlRepo clickUrlRepo;

    @Autowired
    private DescriptionRepo descriptionRepo;

    @Autowired
    private ImageUrlRepo imageUrlRepo;

    @Autowired
    private ImpressionLinkRepo impressionLinkRepo;

    @Autowired
    private NativeAdRepo nativeAdRepo;
}
