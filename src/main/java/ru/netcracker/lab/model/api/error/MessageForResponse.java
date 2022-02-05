package ru.netcracker.lab.model.api.error;

public enum MessageForResponse {

    DELETE("entity deleted"),
    SAVE("the entity is saved"),
    UPDATE("the entity has been updated"),
    FOUND("entity found");
    public String description;

    MessageForResponse(String description) {
        this.description = description;
    }
}
