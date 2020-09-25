package com.example.prohub.model;

public class CnModel {
    private String name, cnid, url,text;

    public CnModel() {
    }

    public CnModel(String name, String cnid, String url, String text) {
        this.name = name;
        this.cnid = cnid;
        this.url = url;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnid() {
        return cnid;
    }

    public void setCnid(String cnid) {
        this.cnid = cnid;
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
