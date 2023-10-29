package com.ambapharm.data.models;

public class MedicationItem extends ListItem {
    private String mainTitle, subtitle, comment;

    public MedicationItem(String mainTitle, String subtitle, String comment) {
        this.mainTitle = mainTitle;
        this.subtitle = subtitle;
        this.comment = comment;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public int getType() {
        return 1; // 1 represents the medication type
    }
}
