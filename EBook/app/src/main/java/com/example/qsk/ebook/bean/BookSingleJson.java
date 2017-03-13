package com.example.qsk.ebook.bean;

/**
 * Created by qsk on 2017/3/10.
 */

public class BookSingleJson {

    /**
     * message : Successfully get book<1>
     * code : 200
     * data : {"update_timestamp":1489039719,"title_en":"7 Killers","word_count":0,"translator":"","title_zh":"七杀手","keywords":"","id":1,"links":{"self":"http://api.beta.wuxiamagic.com/books/1"},"author":"gulong","introduction":"<p>Dragon 5th is a powerful lord with incredible martial arts, and yet is wasting away from a fatal disease. The only medicine which can cure his sickness lies in the clutches of his venemous ex-wife, Madam Lovesickness, and is guarded by 7 dangerous killers, fugitives of the martial world. To retrieve the antidote, Dragon 5th hires Liu Changjie, a skilled martial artist who loves drinking and women, but has a mysterious past. All is not what it seems in this wuxia heist story. Translated by deathblade.<\/p>","cover":"https://s3-us-west-2.amazonaws.com/beta.wuxiamagic/images/book1.jpeg","is_published":true}
     */

    private String message;
    private int code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * update_timestamp : 1489039719
         * title_en : 7 Killers
         * word_count : 0
         * translator :
         * title_zh : 七杀手
         * keywords :
         * id : 1
         * links : {"self":"http://api.beta.wuxiamagic.com/books/1"}
         * author : gulong
         * introduction : <p>Dragon 5th is a powerful lord with incredible martial arts, and yet is wasting away from a fatal disease. The only medicine which can cure his sickness lies in the clutches of his venemous ex-wife, Madam Lovesickness, and is guarded by 7 dangerous killers, fugitives of the martial world. To retrieve the antidote, Dragon 5th hires Liu Changjie, a skilled martial artist who loves drinking and women, but has a mysterious past. All is not what it seems in this wuxia heist story. Translated by deathblade.</p>
         * cover : https://s3-us-west-2.amazonaws.com/beta.wuxiamagic/images/book1.jpeg
         * is_published : true
         */

        private int update_timestamp;
        private String title_en;
        private int word_count;
        private String translator;
        private String title_zh;
        private String keywords;
        private int id;
        private LinksBean links;
        private String author;
        private String introduction;
        private String cover;
        private boolean is_published;

        public int getUpdate_timestamp() {
            return update_timestamp;
        }

        public void setUpdate_timestamp(int update_timestamp) {
            this.update_timestamp = update_timestamp;
        }

        public String getTitle_en() {
            return title_en;
        }

        public void setTitle_en(String title_en) {
            this.title_en = title_en;
        }

        public int getWord_count() {
            return word_count;
        }

        public void setWord_count(int word_count) {
            this.word_count = word_count;
        }

        public String getTranslator() {
            return translator;
        }

        public void setTranslator(String translator) {
            this.translator = translator;
        }

        public String getTitle_zh() {
            return title_zh;
        }

        public void setTitle_zh(String title_zh) {
            this.title_zh = title_zh;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public LinksBean getLinks() {
            return links;
        }

        public void setLinks(LinksBean links) {
            this.links = links;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public boolean isIs_published() {
            return is_published;
        }

        public void setIs_published(boolean is_published) {
            this.is_published = is_published;
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
