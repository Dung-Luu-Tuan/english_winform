package com.example.english.models;

import java.util.Date;
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
@Table(name = "thisinh")
@RequiredArgsConstructor
public class thisinh implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "hoten")
    private String hoten;

    @Column(name = "gioitinh")
    private String gioitinh;

    @Column(name = "ngaysinh")
    private Date ngaysinh;

    @Column(name = "noisinh")
    private String noisinh;

    @Column(name = "cmnd")
    private String cmnd;

    @Column(name = "ngaycap")
    private Date ngaycap;

    @Column(name = "noicap")
    private String noicap;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "thisinh")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<thisinh_phongthi> thisinh_phongthiList;


    public thisinh(int id, String hoten, String gioitinh,
                   Date ngaysinh, String noisinh, String cmnd, Date ngaycap,
                   String noicap, String sdt, String email){
        this.id = id;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.noisinh = noisinh;
        this.cmnd = cmnd;
        this.ngaycap = ngaycap;
        this.noicap = noicap;
        this.sdt = sdt;
        this.email = email;
    }
}
