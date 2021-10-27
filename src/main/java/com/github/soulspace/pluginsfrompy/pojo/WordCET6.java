package com.github.soulspace.pluginsfrompy.pojo;

public class WordCET6 {
    private int id;
    private String word;
    private String phonetic;
    private String paraphrase;

    public WordCET6(int id, String word, String phonetic, String paraphrase) {
        this.id = id;
        this.word = word;
        this.phonetic = phonetic;
        this.paraphrase = paraphrase;
    }

    public String getParaphrase() {
        return paraphrase;
    }

    public void setParaphrase(String paraphrase) {
        this.paraphrase = paraphrase;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WordCET6{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", phonetic='" + phonetic + '\'' +
                ", paraphrase='" + paraphrase + '\'' +
                '}';
    }
}
