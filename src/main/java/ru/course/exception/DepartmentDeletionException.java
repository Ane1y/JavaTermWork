package ru.course.exception;

public class DepartmentDeletionException extends RuntimeException {
    public DepartmentDeletionException(String message) {
        super(message);
    }
}
