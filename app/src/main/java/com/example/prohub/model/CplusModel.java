package com.example.prohub.model;

public class CplusModel {
    private String name, did, url,text;

    public CplusModel() {
    }

    public CplusModel(String name, String did, String url, String text) {
        this.name = name;
        this.did = did;
        this.url = url;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
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
