package com.example.prohub.model;

public class MlModel {
    private String name, mid, url,text;

    public MlModel() {
    }

    public MlModel(String name, String mid, String url, String text) {
        this.name = name;
        this.mid = mid;
        this.url = url;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
