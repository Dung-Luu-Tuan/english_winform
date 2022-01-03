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
@Table(name = "thisinh_phongthi")
@RequiredArgsConstructor
public class thisinh_phongthi implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_thisinh")
    private int id_thisinh;

    @Column(name = "id_phongthi")
    private int id_phongthi;

    @Column(name = "sbd")
    private String sbd;

    @Column(name = "diemnghe")
    private float diemnghe;

    @Column(name = "diemnoi")
    private float diemnoi;

    @Column(name = "diemdoc")
    private float diemdoc;

    @Column(name = "diemviet")
    private float diemviet;

    @ManyToOne
    @JoinColumn(name = "id_thisinh", nullable = false, insertable = false, updatable = false)
    private thisinh thisinh;

    @ManyToOne
    @JoinColumn(name = "id_phongthi", nullable = false, insertable = false, updatable = false)
    private phongthi phongthi;

    public thisinh_phongthi(int id, int id_thisinh, String sbd, float diemnghe, float diemnoi, float diemdoc, float diemviet){
        this.id = id;
        this.id_thisinh = id_thisinh;
        this.sbd = sbd;
        this.diemnghe = diemnghe;
        this.diemnoi = diemnoi;
        this.diemdoc = diemdoc;
        this.diemviet = diemviet;
    }
}
