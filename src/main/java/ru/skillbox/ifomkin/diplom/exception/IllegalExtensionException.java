package ru.skillbox.ifomkin.diplom.exception;

public class IllegalExtensionException extends StorageException{
    public IllegalExtensionException(String message) {
        super(message);
    }

    public IllegalExtensionException(String message, Throwable cause) {
        super(message, cause);
    }
}
