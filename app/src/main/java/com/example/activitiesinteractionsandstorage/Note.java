package com.example.activitiesinteractionsandstorage;

public class Note {
    private String name;
    private String content;

    public Note(String name, String content) {
        this.name = name;
        this.content = content;
    }

    // Getter and setter methods as needed

    @Override
    public String toString() {
        return name; // Display the note name in the ListView
    }
}
