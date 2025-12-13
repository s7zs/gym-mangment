package services.dto;

/**
 * Data Transfer Object for user profile updates
 * Contains fields for all user types (member, trainer, receptionist, physiotherapist)
 */
public class ProfileDTO {
    // Common fields
    private String username;
    private String phone;
    private String address;
    
    // Member-specific fields
    private Integer age;
    private String gender;
    private Integer attendance;
    private Boolean isActive;
    private Boolean freezed;
    private String membershipType;
    private String membershipStart;
    private String membershipEnd;
    
    // Trainer-specific fields
    private String specialization;
    private Integer experienceYears;
    private String workingHours;
    private Double salary;
    
    // Receptionist/Physiotherapist-specific fields
    private String experienceYear;

    // Constructors
    public ProfileDTO() {
    }

    public ProfileDTO(String username, String phone, String address) {
        this.username = username;
        this.phone = phone;
        this.address = address;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getFreezed() {
        return freezed;
    }

    public void setFreezed(Boolean freezed) {
        this.freezed = freezed;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(Integer experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getExperienceYear() {
        return experienceYear;
    }

    public void setExperienceYear(String experienceYear) {
        this.experienceYear = experienceYear;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", attendance=" + attendance +
                ", isActive=" + isActive +
                ", freezed=" + freezed +
                ", membershipType='" + membershipType + '\'' +
                ", membershipStart='" + membershipStart + '\'' +
                ", membershipEnd='" + membershipEnd + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experienceYears=" + experienceYears +
                ", workingHours='" + workingHours + '\'' +
                ", salary=" + salary +
                ", experienceYear='" + experienceYear + '\'' +
                '}';
    }
}
