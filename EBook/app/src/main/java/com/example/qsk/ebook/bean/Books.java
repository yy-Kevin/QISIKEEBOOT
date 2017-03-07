package com.example.qsk.ebook.bean;

/**
 * Created by qsk on 2017/3/6.
 */

public class Books {
    private String author;
    private String title;
    private String content;

    public Books(String title,String author,String content){
        this.author = author;
        this.title = title;
        this.content = content;
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
