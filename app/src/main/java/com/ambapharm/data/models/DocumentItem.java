package com.ambapharm.data.models;

public class DocumentItem extends ListItem {
    private String title;

    public DocumentItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getType() {
        return 0; // 0 represents the document type
    }
}
