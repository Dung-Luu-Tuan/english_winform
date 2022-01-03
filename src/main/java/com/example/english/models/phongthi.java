package com.example.english.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@Entity
@Table(name = "phongthi")
@RequiredArgsConstructor
public class phongthi implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "id_khoathi")
    private int id_khoathi;

    @Column(name = "trinhdo")
    private String trinhdo;

    @Column(name = "cathi")
    private String cathi;

    @OneToMany(mappedBy = "phongthi")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<thisinh_phongthi> thisinh_phongthiList;

    @ManyToOne
    @JoinColumn(name = "id_khoathi", nullable = false, insertable = false, updatable = false)
    private khoathi khoathi;

    @OneToMany(mappedBy = "phongthi")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<coithi> coithiList;

}
