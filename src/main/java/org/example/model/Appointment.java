package org.example.model;

public class Appointment {
    private String appointmentName;
    private String memberId;
    private String trainerId; // or physiotherapist / doctor
    private String date; // yyyy-mm-dd
    private String time; // HH:MM
    private String status; // scheduled, cancelled, completed
    private String note;

    public Appointment() {}

    public String getAppointmentName() { return appointmentName; }
    public void setAppointmentName(String appointmentName) { this.appointmentName = appointmentName; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public String getTrainerId() { return trainerId; }
    public void setTrainerId(String trainerId) { this.trainerId = trainerId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
