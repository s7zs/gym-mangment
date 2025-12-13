package services;

import org.example.model.*;
import org.example.repo.Userrepo;
import services.dto.ProfileDTO;

public class commonservices {
    
    private final Userrepo userRepo;

    public commonservices(Userrepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Register/Signup a new user with specified role using Factory Pattern
     * @param role Desired role for the user (member, trainer, receptionist, physiotherapist)
     * @param username Unique username
     * @param password User password
     * @return User object if signup successful, null if username already exists
     */
    public users signup(Roles role, String username, String password) {
        // Check if username already exists
        if (userRepo.exists(username)) {
            System.out.println("Signup failed: Username '" + username + "' already exists");
            return null;
        }
        
        // Validate inputs
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Signup failed: Username cannot be empty");
            return null;
        }
        
        if (password == null || password.trim().isEmpty()) {
            System.out.println("Signup failed: Password cannot be empty");
            return null;
        }
        
        if (role == null) {
            System.out.println("Signup failed: Role must be specified");
            return null;
        }
        
        try {
            // Create user using Factory Pattern through repository
            String userId = userRepo.createUser(role, username, password);
            
            // Retrieve and return the created user
            users newUser = userRepo.findByUsername(username);
            
            if (newUser != null) {
                System.out.println("Signup successful: User '" + username + "' created with role " + role + " (ID: " + userId + ")");
            }
            
            return newUser;
        } catch (Exception e) {
            System.out.println("Signup failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Login user with username and password
     * @param username User's username
     * @param password User's password
     * @return User object if credentials are valid, null otherwise
     */
    public users login(String username, String password) {
        users user = userRepo.findByUsername(username);
        
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        
        return null;
    }

    /**
     * Login user with username, password, and expected role verification
     * @param username User's username
     * @param password User's password
     * @param expectedRole Expected role for additional verification
     * @return User object if credentials and role are valid, null otherwise
     */
    public users login(String username, String password, Roles expectedRole) {
        users user = userRepo.findByUsername(username);
        
        if (user != null && user.getPassword().equals(password)) {
            // Verify the role matches the expected role
            if (user.getRole() == expectedRole) {
                return user;
            } else {
                System.out.println("Login failed: User role '" + user.getRole() + "' does not match expected role '" + expectedRole + "'");
                return null;
            }
        }
        
        return null;
    }

    /**
     * Logout user
     * @param username User's username
     */
    public void logout(String username) {
        // In a stateless application, logout typically means invalidating the session/token
        // This is a placeholder for logout logic (e.g., clearing session, invalidating token)
        System.out.println("User " + username + " logged out successfully");
    }

    /**
     * Update user profile
     * @param username User's username
     * @param profileData Profile data to update
     * @return Updated user object if successful, null otherwise
     */
    public users updateProfile(String username, ProfileDTO profileData) {
        users user = userRepo.findByUsername(username);
        
        if (user == null) {
            return null;
        }

        // Update common fields
        if (profileData.getUsername() != null && !profileData.getUsername().equals(username)) {
            user.setUsername(profileData.getUsername());
        }

        // Update role-specific fields
        switch (user.getRole()) {
            case member:
                if (user instanceof member) {
                    member memberUser = (member) user;
                    if (profileData.getPhone() != null) memberUser.setPhone(profileData.getPhone());
                    if (profileData.getAddress() != null) memberUser.address = profileData.getAddress();
                    if (profileData.getAge() != null) memberUser.age = profileData.getAge();
                    if (profileData.getGender() != null) memberUser.gender = profileData.getGender();
                    if (profileData.getAttendance() != null) memberUser.attendance = profileData.getAttendance();
                    if (profileData.getIsActive() != null) memberUser.isActive = profileData.getIsActive();
                    if (profileData.getFreezed() != null) memberUser.freezed = profileData.getFreezed();
                    if (profileData.getMembershipType() != null) memberUser.setMembershipType(profileData.getMembershipType());
                    if (profileData.getMembershipStart() != null) memberUser.setMembershipStart(profileData.getMembershipStart());
                    if (profileData.getMembershipEnd() != null) memberUser.setMembershipEnd(profileData.getMembershipEnd());
                }
                break;
                
            case trainer:
                if (user instanceof trainer) {
                    trainer trainerUser = (trainer) user;
                    if (profileData.getSpecialization() != null) trainerUser.setSpecialization(profileData.getSpecialization());
                    if (profileData.getExperienceYears() != null) trainerUser.setExperienceYears(profileData.getExperienceYears());
                    if (profileData.getWorkingHours() != null) trainerUser.setWorkingHours(profileData.getWorkingHours());
                    if (profileData.getSalary() != null) trainerUser.setSalary(profileData.getSalary());
                }
                break;
                
            case receptionist:
                if (user instanceof receptionist) {
                    receptionist receptionistUser = (receptionist) user;
                    if (profileData.getPhone() != null) receptionistUser.setPhone(profileData.getPhone());
                    if (profileData.getExperienceYear() != null) receptionistUser.setExperienceYear(profileData.getExperienceYear());
                }
                break;
                
            case physiotherapist:
                // Add physiotherapist specific fields if needed
                break;
        }

        // Save updated user
        boolean updated = userRepo.update(username, user);
        
        return updated ? user : null;
    }

    /**
     * Change user password
     * @param username User's username
     * @param oldPassword Current password
     * @param newPassword New password
     * @return true if password changed successfully, false otherwise
     */
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        users user = userRepo.findByUsername(username);
        
        if (user == null) {
            return false;
        }

        // Verify old password
        if (!user.getPassword().equals(oldPassword)) {
            return false;
        }

        // Validate new password
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return false;
        }

        // Update password
        user.setPassword(newPassword);
        return userRepo.update(username, user);
    }

    /**
     * Reset user password to a default value
     * @param username User's username
     * @return true if password reset successfully, false otherwise
     */
    public boolean resetPassword(String username) {
        users user = userRepo.findByUsername(username);
        
        if (user == null) {
            return false;
        }

        // Reset password to default (e.g., "password123" or username)
        String defaultPassword = "password123";
        user.setPassword(defaultPassword);
        
        boolean updated = userRepo.update(username, user);
        
        if (updated) {
            System.out.println("Password for user " + username + " has been reset to: " + defaultPassword);
        }
        
        return updated;
    }

    /**
     * Get user profile by username
     * @param username User's username
     * @return User object if found, null otherwise
     */
    public users getProfile(String username) {
        return userRepo.findByUsername(username);
    }
}
