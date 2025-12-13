package org.example.model;
public class receptionist extends users{
private String phone;
private String experienceYear;


    public receptionist(Roles role, String password, String username, String phone, String experienceYear) {
        super(Roles.receptionist, password, username);
        
        this.phone = phone;
        this.experienceYear = experienceYear;
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


   
    public receptionist( String password, String username) {
        super(Roles.receptionist, password, username);
        this.phone = "0";
        this.experienceYear = "0y";
    }
}
