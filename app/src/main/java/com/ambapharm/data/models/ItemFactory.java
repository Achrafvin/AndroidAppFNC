package com.ambapharm.data.models;

public class ItemFactory {
    public ListItem createDocument(String title) {
        return new DocumentItem(title);
    }

    public ListItem createMedication(String mainTitle, String subtitle, String comment) {
        return new MedicationItem(mainTitle, subtitle, comment);
    }
}

