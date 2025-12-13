package org.example.model;
import org.example.model.Roles;
public class member extends users {

    public int age;
    public String gender;
    public String address;
    public int attendance;
    public boolean isActive = true;
    public boolean freezed = false;
    public String phone;

    public String membershipType;
    public String membershipStart; 
    public String membershipEnd;   

    public member(Roles role, String password, String username, String phone, String email, String membershipType, String membershipStart, String membershipEnd) {
        super(Roles.member, password, username);
        this.phone = phone;

        this.membershipType = membershipType;
        this.membershipStart = membershipStart;
        this.membershipEnd = membershipEnd;
    }

    public member( String password, String username) {
        super(Roles.member, password, username);
        this.phone = "0";
     
        this.membershipType = "null";
        this.membershipStart = "0";
        this.membershipEnd = "0";
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipStart() {
        return membershipStart;
    }

    public void setMembershipStart(String membershipStart) {
        this.membershipStart = membershipStart;
    }

    public String getMembershipEnd() {
        return membershipEnd;
    }

    public void setMembershipEnd(String membershipEnd) {
        this.membershipEnd = membershipEnd;
    }


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
