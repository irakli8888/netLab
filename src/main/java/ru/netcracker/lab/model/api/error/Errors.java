package ru.netcracker.lab.model.api.error;

public enum Errors {
    INVALID_REQUEST("incredible error");
    public String description;

    Errors(String description) {
        this.description = description;
    }
}
