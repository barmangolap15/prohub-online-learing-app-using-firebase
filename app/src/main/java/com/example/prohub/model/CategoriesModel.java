package com.example.prohub.model;

public class CategoriesModel {
    private String catid, name, image;

    public CategoriesModel() {
    }

    public CategoriesModel(String catid, String name, String image) {
        this.catid = catid;
        this.name = name;
        this.image = image;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
