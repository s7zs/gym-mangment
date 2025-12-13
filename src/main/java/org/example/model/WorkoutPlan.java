package org.example.model;

public class WorkoutPlan {

    private String planId;
    private String trainerId;
    private String planName;
    private String planDetails;

    public WorkoutPlan() {}

    public String getPlanId() { return planId; }
    public void setPlanId(String planId) { this.planId = planId; }

    public String getTrainerId() { return trainerId; }
    public void setTrainerId(String trainerId) { this.trainerId = trainerId; }

    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }

    public String getPlanDetails() { return planDetails; }
    public void setPlanDetails(String planDetails) { this.planDetails = planDetails; }
}
