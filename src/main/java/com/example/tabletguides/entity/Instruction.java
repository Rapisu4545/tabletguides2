package com.example.tabletguides.entity;

import javax.persistence.*;

@Entity
public class Instruction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String head;

    @Column(length = 10000)
    private String main;
    private String images;
    private String tag;
    private String category;

    public Instruction(){

    }

    public Instruction(String head, String main, String images, String tag, String category) {
        this.head = head;
        this.main = main;
        this.images = images;
        this.tag = tag;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
