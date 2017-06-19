package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
//@Table(uniqueConstraints=@UniqueConstraint(columnNames="title"))
public class NativeAd {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String title;

    @OneToMany(mappedBy = "nativeAd", orphanRemoval = true)
    private List<ImpressionLink> impressionLinks;

    @OneToMany(mappedBy = "nativeAd", orphanRemoval = true)
    private List<ImageUrl> imageUrls;

    @OneToMany(mappedBy = "nativeAd", orphanRemoval = true)
    private List<ClickUrl> clickUrls;

    @OneToMany(mappedBy = "nativeAd", orphanRemoval = true)
    private List<Description> descriptions;
}
