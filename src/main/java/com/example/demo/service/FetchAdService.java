package com.example.demo.service;

import com.example.demo.domain.NativeAd;
import com.example.demo.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FetchAdService {

    public String fetchAdByTitle(String title) {
        this.title = title;
        NativeAd nativeAd = findNativeAd();
        if (nativeAd != null) {
            log.info(nativeAd.getTitle());
            log.info("size : " + clickUrlRepo.findByNativeAdTitle(nativeAd.getTitle()).size());
        }
        return "";
    }

    private NativeAd findNativeAd() {
        return nativeAdRepo.
                findByTitle(title).
                orElseGet(() -> nativeAdRepo.findAll()
                        .stream()
                        .filter(nativeAd -> nativeAd.getTitle().contains(title))
                        .findAny().orElse(null));
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
