package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ClickUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "nativeAd")
    @ManyToOne(optional = false)
    private NativeAd nativeAd;

    @Lob
    private String url;
}
