package com.example.prohub.model;

public class CModel {
    private String name, cid, url,text;

    public CModel() {
    }

    public CModel(String name, String cid, String url, String text) {
        this.name = name;
        this.cid = cid;
        this.url = url;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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
