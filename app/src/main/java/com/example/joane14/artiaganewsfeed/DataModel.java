package com.example.joane14.artiaganewsfeed;

/**
 * Created by Joane14 on 07/02/2017.
 */

public class DataModel {
    private String title, section, url;

    public DataModel(){

    }

    public DataModel(String title, String section, String url){
        this.title=title;
        this.section = section;
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public String getUrl() {
        return url;
    }
}
