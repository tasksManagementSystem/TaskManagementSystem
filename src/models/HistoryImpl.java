package models;

import models.contracts.History;

public class HistoryImpl implements History {
    private String description;


    public HistoryImpl() {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;

    }

    public HistoryImpl(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public  String viewInfo() {
        return String.format("%s",description);
    }

    @Override
    public String toString() {
        return description;
    }
}