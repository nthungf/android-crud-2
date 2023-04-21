package com.example.crud2.model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String ten;
    private String tacGia;
    private String phamVi;
    private String doiTuong;
    private int danhGia;

    public Book() {
    }

    public Book(int id, String ten, String tacGia, String phamVi, String doiTuong, int danhGia) {
        this.id = id;
        this.ten = ten;
        this.tacGia = tacGia;
        this.phamVi = phamVi;
        this.doiTuong = doiTuong;
        this.danhGia = danhGia;
    }

    public Book(String ten, String tacGia, String phamVi, String doiTuong, int danhGia) {
        this.ten = ten;
        this.tacGia = tacGia;
        this.phamVi = phamVi;
        this.doiTuong = doiTuong;
        this.danhGia = danhGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getPhamVi() {
        return phamVi;
    }

    public void setPhamVi(String phamVi) {
        this.phamVi = phamVi;
    }

    public String getDoiTuong() {
        return doiTuong;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    public int getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(int danhGia) {
        this.danhGia = danhGia;
    }
}
