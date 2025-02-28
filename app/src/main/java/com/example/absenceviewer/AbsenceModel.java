package com.example.absenceviewer;

public class AbsenceModel {
    private String studentName;
    private String date;
    private String status;
    private boolean alert;

    public AbsenceModel(String studentName, String date, String status) {
        this.studentName = studentName;
        this.date = date;
        this.status = status;
        this.alert = false;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }
}
