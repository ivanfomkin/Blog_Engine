package ru.skillbox.ifomkin.diplom.exception.storage;

public class IllegalSizeException extends StorageException{
    public IllegalSizeException(String message) {
        super(message);
    }

    public IllegalSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
