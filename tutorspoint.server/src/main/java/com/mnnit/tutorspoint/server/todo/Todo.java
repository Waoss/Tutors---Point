package com.mnnit.tutorspoint.server.todo;

public class Todo {
    private String student;
    private String message;

    public Todo() {
    }

    public Todo(final String student, final String message) {
        this.student = student;
        this.message = message;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(final String student) {
        this.student = student;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
