package com.example.prohub.model;

public class PythonModel {
    private String name, pid, url,text;

    public PythonModel() {
    }

    public PythonModel(String name, String pid, String url, String text) {
        this.name = name;
        this.pid = pid;
        this.url = url;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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
