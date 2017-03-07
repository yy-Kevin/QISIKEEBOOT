package com.example.qsk.ebook.bean;

import java.util.List;

/**
 * Created by qsk on 2017/3/7.
 */

public class BooksJson {

    /**
     * message : Successfully get 7 books
     * code : 200
     * data : [{"translator":null,"title_zh":null,"links":{"self":"http://api.beta.wuxiamagic.com/books/1"},"update_timestamp":0,"author":null,"introduction":null,"cover":null,"title_en":"7 Killers","id":1,"chapters":0},{"translator":null,"title_zh":null,"links":{"self":"http://api.beta.wuxiamagic.com/books/2"},"update_timestamp":0,"author":null,"introduction":null,"cover":null,"title_en":"Child of Light","id":2,"chapters":0},{"translator":null,"title_zh":null,"links":{"self":"http://api.beta.wuxiamagic.com/books/3"},"update_timestamp":0,"author":null,"introduction":null,"cover":null,"title_en":"Coiling Dragon","id":3,"chapters":0},{"translator":null,"title_zh":null,"links":{"self":"http://api.beta.wuxiamagic.com/books/4"},"update_timestamp":0,"author":null,"introduction":null,"cover":null,"title_en":"Dragon King With Seven Stars","id":4,"chapters":0},{"translator":null,"title_zh":null,"links":{"self":"http://api.beta.wuxiamagic.com/books/5"},"update_timestamp":0,"author":null,"introduction":null,"cover":null,"title_en":"Heroes Shed No Tears","id":5,"chapters":0},{"translator":null,"title_zh":null,"links":{"self":"http://api.beta.wuxiamagic.com/books/6"},"update_timestamp":0,"author":null,"introduction":null,"cover":null,"title_en":"Horizon, Bright Moon, Sabre","id":6,"chapters":25},{"translator":null,"title_zh":null,"links":{"self":"http://api.beta.wuxiamagic.com/books/7"},"update_timestamp":0,"author":null,"introduction":null,"cover":null,"title_en":"Stellar Transformations","id":7,"chapters":0}]
     */

    private String message;
    private int code;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * translator : null
         * title_zh : null
         * links : {"self":"http://api.beta.wuxiamagic.com/books/1"}
         * update_timestamp : 0
         * author : null
         * introduction : null
         * cover : null
         * title_en : 7 Killers
         * id : 1
         * chapters : 0
         */

        private Object translator;
        private Object title_zh;
        private LinksBean links;
        private int update_timestamp;
        private Object author;
        private Object introduction;
        private Object cover;
        private String title_en;
        private int id;
        private int chapters;

        public Object getTranslator() {
            return translator;
        }

        public void setTranslator(Object translator) {
            this.translator = translator;
        }

        public Object getTitle_zh() {
            return title_zh;
        }

        public void setTitle_zh(Object title_zh) {
            this.title_zh = title_zh;
        }

        public LinksBean getLinks() {
            return links;
        }

        public void setLinks(LinksBean links) {
            this.links = links;
        }

        public int getUpdate_timestamp() {
            return update_timestamp;
        }

        public void setUpdate_timestamp(int update_timestamp) {
            this.update_timestamp = update_timestamp;
        }

        public Object getAuthor() {
            return author;
        }

        public void setAuthor(Object author) {
            this.author = author;
        }

        public Object getIntroduction() {
            return introduction;
        }

        public void setIntroduction(Object introduction) {
            this.introduction = introduction;
        }

        public Object getCover() {
            return cover;
        }

        public void setCover(Object cover) {
            this.cover = cover;
        }

        public String getTitle_en() {
            return title_en;
        }

        public void setTitle_en(String title_en) {
            this.title_en = title_en;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getChapters() {
            return chapters;
        }

        public void setChapters(int chapters) {
            this.chapters = chapters;
        }

        public static class LinksBean {
            /**
             * self : http://api.beta.wuxiamagic.com/books/1
             */

            private String self;

            public String getSelf() {
                return self;
            }

            public void setSelf(String self) {
                this.self = self;
            }
        }
    }
}
