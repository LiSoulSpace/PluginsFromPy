package com.github.soulspace.pluginsfrompy.pojo;

import org.apache.ibatis.type.Alias;

public class PoemTang {
    private int value;
    private String author;
    private String paragraphs;
    private String title;
    private String id;

    public PoemTang(int value, String author, String paragraphs, String title, String id) {
        this.value = value;
        this.author = author;
        this.paragraphs = paragraphs;
        this.title = title;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(String paragraphs) {
        this.paragraphs = paragraphs;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "User{" +
                "value=" + value +
                ", author='" + author + '\'' +
                ", paragraphs='" + paragraphs + '\'' +
                ", title='" + title + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
