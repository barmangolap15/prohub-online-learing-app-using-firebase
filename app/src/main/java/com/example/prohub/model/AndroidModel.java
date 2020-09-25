package com.example.prohub.model;

public class AndroidModel {

    private String aid, name, text, url;

    public AndroidModel() {
    }

    public AndroidModel(String aid, String name, String text, String url) {
        this.aid = aid;
        this.name = name;
        this.text = text;
        this.url = url;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
