package com.github.soulspace.pluginsfrompy.pojo;

import org.apache.ibatis.type.Alias;

public class Ci {
    private int value;
    private String rhythmic;
    private String author;
    private String content;

    public Ci(int value, String rhythmic, String author, String content) {
        this.value = value;
        this.rhythmic = rhythmic;
        this.author = author;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRhythmic() {
        return rhythmic;
    }

    public void setRhythmic(String rhythmic) {
        this.rhythmic = rhythmic;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Ci{" +
                "value=" + value +
                ", rhythmic='" + rhythmic + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
