package com.example.english.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@Entity
@Table(name = "coithi")
@RequiredArgsConstructor
public class coithi implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_phongthi")
    private int id_phongthi;

    @Column(name = "id_giaovien")
    private int id_giaovien;

    @ManyToOne
    @JoinColumn(name = "id_phongthi", nullable = false, insertable = false, updatable = false)
    private phongthi phongthi;

    @ManyToOne
    @JoinColumn(name = "id_giaovien", nullable = false, insertable = false, updatable = false)
    private giaovien giaovien;
}
