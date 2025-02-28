package com.example.absenceviewer;

public class Student {
    private String name;
    private String date;
    private String status;
    private String subject;

    public Student(String name, String date, String status, String subject) {
        this.name = name;
        this.date = date;
        this.status = status;
        this.subject = subject;
    }

    public String getName() { return name; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
    public String getSubject() { return subject; }
}