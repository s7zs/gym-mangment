package org.example.model;
public class receptionist extends users{
    private String fullName;
    private String phone;

    public receptionist(Roles role, String password, String username, String fullName, String phone, String experienceYear) {
        super(Roles.receptionist, password, username);
        this.fullName = fullName;
        this.phone = phone;
        this.experienceYear = experienceYear;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(String experienceYear) {
        this.experienceYear = experienceYear;
    }


    private String experienceYear;
    public receptionist(Roles role, String password, String username) {
        super(Roles.receptionist, password, username);
    }
}
