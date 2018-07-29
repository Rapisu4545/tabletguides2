package com.example.tabletguides.entity;

import javax.persistence.*;

@Entity
public class Instruction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String head;

    @Column(length = 10000)
    private String main;
    private String images;
    private String noimages;
    private String tag;


    private Integer category;

    public Instruction(){

    }

    public Instruction(String head, String main, String images, String noimages, String tag, Integer category) {
        this.head = head;
        this.main = main;
        this.images = images;
        this.tag = tag;
        this.category = category;
        this.noimages = noimages;
    }

    public String getNoimages() {
        return noimages;
    }

    public void setNoimages(String noimages) {
        this.noimages = noimages;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
