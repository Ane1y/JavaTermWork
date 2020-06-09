package ru.course.exception;

public class DepartmentsEmployeesNotFoundException extends RuntimeException {
    public DepartmentsEmployeesNotFoundException(String message) {
        super(message);
    }
}
