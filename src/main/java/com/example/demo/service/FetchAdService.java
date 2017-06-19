package com.example.demo.service;

import com.example.demo.domain.NativeAd;
import com.example.demo.repository.*;
import groovy.json.JsonOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FetchAdService {

    public String fetchAdByTitle(String title) {
        this.title = title;
        NativeAd nativeAd = findNativeAd();
        if (nativeAd != null) {
            return nativeAdToJson(nativeAd);
        }
        return "{}";
    }

    private NativeAd findNativeAd() {
        return nativeAdRepo.
                findByTitle(title).
                orElseGet(() -> nativeAdRepo.findAll()
                        .stream()
                        .filter(nativeAd -> nativeAd.getTitle().contains(title))
                        .findAny().orElse(null));
    }

    private String nativeAdToJson(NativeAd nativeAd) {
        Map obj = new HashMap();
        obj.put("title", fromHex(nativeAd.getTitle()));
        obj.put("clickUrls", findClickUrls(nativeAd));
        obj.put("descriptions", findDescriptions(nativeAd));
        obj.put("imageUrls", findImageUrls(nativeAd));
        obj.put("impressionLinks", findImpressionLinks(nativeAd));
        String json = JsonOutput.toJson(obj);
        return JsonOutput.prettyPrint(json);
    }

    private String fromHex(String hex) {
        hex = hex.replaceAll("^(00)+", "");
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        return new String(bytes);
    }

    private List<String> findClickUrls(NativeAd nativeAd) {
        return clickUrlRepo
                .findByNativeAdId(nativeAd.getId())
                .stream()
                .map(obj -> obj.getUrl())
                .collect(Collectors.toList());
    }

    private List<String> findDescriptions(NativeAd nativeAd) {
        return descriptionRepo
                .findByNativeAdId(nativeAd.getId())
                .stream()
                .map(obj -> obj.getText())
                .collect(Collectors.toList());
    }

    private List<String> findImageUrls(NativeAd nativeAd) {
        return imageUrlRepo
                .findByNativeAdId(nativeAd.getId())
                .stream()
                .map(obj -> obj.getUrl())
                .collect(Collectors.toList());
    }

    private List<String> findImpressionLinks(NativeAd nativeAd) {
        return impressionLinkRepo
                .findByNativeAdId(nativeAd.getId())
                .stream()
                .map(obj -> obj.getLink())
                .collect(Collectors.toList());
    }

    private String title;

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
