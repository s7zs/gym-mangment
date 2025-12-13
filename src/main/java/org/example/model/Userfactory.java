package org.example.model;

public  class Userfactory {
    public static users createUser(Roles role, String username, String password) {
        switch (role) {
            case trainer:
                return new trainer(username, password);
            case member:
                return new member(username, password);
            case receptionist:
                return new receptionist(username, password);
            case physiotherapist:
                return new physitherapist(username, password);
            default:
                throw new IllegalArgumentException("Invalid user role: " + role);
        }
    }
    
}
