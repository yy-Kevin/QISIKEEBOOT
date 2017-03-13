package com.example.qsk.ebook.bean;

/**
 * Created by qsk on 2017/3/10.
 */

public class BookSingle {

    private String author;
    private String bookName;
    private String imUrl;
    private String chapter;
    private String toView;
    private String click;
    //作者  书名  封面Url  章节  查看  点击量

    /**
     *
     * @param author 作者
     * @param bookName 书名字
     * @param imUrl 书封面图片路径
     * @param chapter 书的简介
     * @param toView 书查看数目
     * @param click  书的点击量
     */
    public BookSingle(String author, String bookName,String imUrl,String chapter,String toView,String click){
        this.author = author;
        this.bookName = bookName;
        this.imUrl = imUrl;
        this.chapter = chapter;
        this.toView = toView;
        this.click = click;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImUrl() {
        return imUrl;
    }

    public void setImUrl(String imUrl) {
        this.imUrl = imUrl;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getToView() {
        return toView;
    }

    public void setToView(String toView) {
        this.toView = toView;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }
}
