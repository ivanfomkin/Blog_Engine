package ru.skillbox.ifomkin.diplom.model.enumerated;

public enum Role {
    USER,
    MODERATOR;

    @Override
    public String toString() {
        return name();
    }
}
