package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class NativeAd {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "nativeAd", orphanRemoval = true)
    private List<ImpressionLink> impressionLinks;

    @OneToMany(mappedBy = "nativeAd", orphanRemoval = true)
    private List<ImageUrl> imageUrls;

    private String title;
}
