package org.example.model;

public class trainer extends users{

    public trainer(Roles role, String password, String username) {
        super(Roles.trainer, password, username);
    }
}
