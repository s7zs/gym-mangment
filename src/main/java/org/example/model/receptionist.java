package org.example.model;

public class receptionist extends users{
    public receptionist(Roles role, String password, String username) {
        super(Roles.receptionist, password, username);
    }
}
