package com.example.english.models;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@Entity
@Table(name = "khoathi")
@RequiredArgsConstructor
public class khoathi implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tenkhoathi")
    private String tenkhoathi;

    @Column(name = "ngaythi")
    @Temporal(TemporalType.DATE)
    private Date ngaythi;

    @OneToMany(mappedBy = "khoathi")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<phongthi> phongthiList;

    public khoathi(int id, String tenkhoathi, Date ngaythi) {
        this.id = id;
        this.tenkhoathi = tenkhoathi;
        this.ngaythi = ngaythi;
    }
}
