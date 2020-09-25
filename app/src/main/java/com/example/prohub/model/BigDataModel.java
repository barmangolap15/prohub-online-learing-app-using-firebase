package com.example.prohub.model;

public class BigDataModel {
    private String name, bdid, url,text;

    public BigDataModel() {
    }

    public BigDataModel(String name, String bdid, String url, String text) {
        this.name = name;
        this.bdid = bdid;
        this.url = url;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBdid() {
        return bdid;
    }

    public void setBdid(String bdid) {
        this.bdid = bdid;
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
