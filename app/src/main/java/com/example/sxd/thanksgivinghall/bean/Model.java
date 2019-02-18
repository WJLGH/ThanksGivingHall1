package com.example.sxd.thanksgivinghall.bean;

/**
 * Created by sxd on 2018/1/30.
 */

public class Model {
    private String title;
    private int imgUrl;
    private  Class page;
    public Class getPage() {
        return page;
    }

    public void setPage(Class page) {
        this.page = page;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Model(String title, int imgUrl, Class page) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.page = page;
    }

    public Model() {
    }
}
