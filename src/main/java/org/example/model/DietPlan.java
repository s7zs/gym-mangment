package org.example.model;

public class DietPlan {

    private String dietId;
    private String trainerId;
    private String dietName;
    private String dietDetails;

    public DietPlan() {}

    public String getDietId() { return dietId; }
    public void setDietId(String dietId) { this.dietId = dietId; }

    public String getTrainerId() { return trainerId; }
    public void setTrainerId(String trainerId) { this.trainerId = trainerId; }

    public String getDietName() { return dietName; }
    public void setDietName(String dietName) { this.dietName = dietName; }

    public String getDietDetails() { return dietDetails; }
    public void setDietDetails(String dietDetails) { this.dietDetails = dietDetails; }
}
