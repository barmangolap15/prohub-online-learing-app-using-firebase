package com.example.prohub.model;

public class JavaModel {
    private String name, jid, url,text;

    public JavaModel() {
    }

    public JavaModel(String name, String jid, String url, String text) {
        this.name = name;
        this.jid = jid;
        this.url = url;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
