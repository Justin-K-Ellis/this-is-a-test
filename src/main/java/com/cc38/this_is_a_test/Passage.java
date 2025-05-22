package com.cc38.this_is_a_test;

public class Passage {
    private String title;
    private String content;

    public Passage(String title, String content) {
        this.title = title;
        this.content = content;
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
