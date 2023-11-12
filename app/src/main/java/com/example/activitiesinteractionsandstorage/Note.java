package com.example.activitiesinteractionsandstorage;

public class Note {
    private String name;
    private String content;

    public Note(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public String toString() {
        return name;
    }
}
