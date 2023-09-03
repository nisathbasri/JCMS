package com.jobconsultationmanagement.model;

public class Appointment {
    private int appointmentId;
    private int jobseekerId;
    private int consultantId;
    private String date;
    private String time;
    private String notes;

    public Appointment() {
    }

    public Appointment(int appointmentId, int jobseekerId, int consultantId, String date, String time, String notes) {
        this.appointmentId = appointmentId;
        this.jobseekerId = jobseekerId;
        this.consultantId = consultantId;
        this.date = date;
        this.time = time;
        this.notes = notes;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getJobseekerId() {
        return jobseekerId;
    }

    public void setJobseekerId(int jobseekerId) {
        this.jobseekerId = jobseekerId;
    }

    public int getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(int consultantId) {
        this.consultantId = consultantId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
