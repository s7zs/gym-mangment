package org.example.model;

public class trainer extends users{
    private String specialization;
    private int experienceYears;
    private String workingHours;
    private double salary;

    public trainer(Roles role, String password, String username, String name, String specialization, int experienceYears, String workingHours, double salary) {
        super(Roles.trainer, password, username);
    
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.workingHours = workingHours;
        this.salary = salary;
    }
    public trainer(String password, String username) {
        super(Roles.trainer, password, username);
        
        this.specialization = "null";
        this.experienceYears = 0;
        this.workingHours = "0h";
        this.salary = 0;
    }


    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public trainer(Roles Roles, String password, String username) {
        super(Roles.trainer, password, username);
    }
}
