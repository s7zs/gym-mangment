package org.example.model;
import org.example.model.Roles;
public class member extends users {

    public int age;
    public String gender;
    public String address;
    public int attendance;
    public boolean isActive = true;
    public boolean freezed = false;
    

    public member(Roles role, String password, String username, int age, String gender, String address, int attendance, boolean isActive, boolean freezed) {
        super(Roles.member, password, username);
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.attendance = attendance;
        this.isActive = isActive;
        this.freezed = freezed;
    }


}
