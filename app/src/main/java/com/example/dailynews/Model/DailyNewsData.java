package com.example.dailynews.Model;

import java.util.Date;

public class DailyNewsData {
    private String type;
    private String sectionName;
    private String webPublicationDate;
    private String webTitle;
    private String webUrl;
    private String placeHolderImg;

    public DailyNewsData(){

    }

    public DailyNewsData(String type, String sectionName, String webPublicationDate, String webTitle, String webUrl) {
        this.type = type;
        this.sectionName = sectionName;
        this.webPublicationDate = webPublicationDate;
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.placeHolderImg = placeHolderImg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getPlaceHolderImg() {
        return placeHolderImg;
    }

    public void setPlaceHolderImg(String placeHolderImg) {
        this.placeHolderImg = placeHolderImg;
    }
}
