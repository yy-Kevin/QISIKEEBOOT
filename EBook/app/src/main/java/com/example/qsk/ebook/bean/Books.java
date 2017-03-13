package com.example.qsk.ebook.bean;

import android.widget.ImageView;

/**
 * Created by qsk on 2017/3/6.
 */

public class Books {
    private String author;
    private String title;
    private String content;
    private String imUrl;

    public String getImUrl() {
        return imUrl;
    }

    public void setImUrl(String imUrl) {
        this.imUrl = imUrl;
    }

    public Books(String title, String author, String content, String imUrl){
        this.author = author;
        this.title = title;
        this.content = content;
        this.imUrl = imUrl;

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
