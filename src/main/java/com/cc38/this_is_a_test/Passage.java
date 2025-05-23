package com.cc38.this_is_a_test;

public class Passage {
    private final String id;
    private String title;
    private String content;

    public Passage(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public void updateTitle(String data) {
        this.title = data;
    }

    public void updateContent(String data) {
        this.content = data;
    }
}
