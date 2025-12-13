package org.example.model;

public class trainer extends users{

    private String name;
    private String specialization;
    private int experienceYears;
    private String workingHours;
    private double salary;

    public trainer(Roles role, String password, String username, String name, String specialization, int experienceYears, String workingHours, double salary) {
        super(Roles.trainer, password, username);
        this.name = name;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.workingHours = workingHours;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public trainer(Roles role, String password, String username) {
        super(Roles.trainer, password, username);
    }
}
