package org.example.repo;
import org.example.model.DietPlan;
import org.example.model.WorkoutPlan;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TrainerRepository {

    private final MongoCollection<Document> trainers;
    private final MongoCollection<Document> workouts;
    private final MongoCollection<Document> diets;
    private final MongoCollection<Document> sessions;
    private final MongoCollection<Document> progress;

    public TrainerRepository(
            MongoDatabase db,
            String trainerColl,
            String workoutColl,
            String dietColl,
            String sessionColl,
            String progressColl
    ) {
        this.trainers = db.getCollection(trainerColl);
        this.workouts = db.getCollection(workoutColl);
        this.diets = db.getCollection(dietColl);
        this.sessions = db.getCollection(sessionColl);
        this.progress = db.getCollection(progressColl);
    }

    // ---------------- WORKOUT PLANS ----------------
    public void saveWorkout(WorkoutPlan w) {
        workouts.insertOne(workoutToDoc(w));
    }

    public void updateWorkout(WorkoutPlan w) {
        workouts.replaceOne(Filters.eq("planId", w.getPlanId()), workoutToDoc(w));
    }

    public List<WorkoutPlan> findAllWorkouts() {
        List<WorkoutPlan> list = new ArrayList<>();
        for (Document doc : workouts.find()) {
            list.add(workoutFromDoc(doc));
        }
        return list;
    }

    public WorkoutPlan findWorkoutByName(String name) {
        Document doc = workouts.find(Filters.eq("planName", name)).first();
        return doc == null ? null : workoutFromDoc(doc);
    }

    // ---------------- DIET PLANS ----------------
    public void saveDiet(DietPlan d) {
        diets.insertOne(dietToDoc(d));
    }

    public void updateDiet(DietPlan d) {
        diets.replaceOne(Filters.eq("dietId", d.getDietId()), dietToDoc(d));
    }

    public List<DietPlan> findAllDiets() {
        List<DietPlan> list = new ArrayList<>();
        for (Document doc : diets.find()) {
            list.add(dietFromDoc(doc));
        }
        return list;
    }

    public DietPlan findDietByName(String name) {
        Document doc = diets.find(Filters.eq("dietName", name)).first();
        return doc == null ? null : dietFromDoc(doc);
    }

    // ---------------- DOCUMENT CONVERSION ----------------

    private Document workoutToDoc(WorkoutPlan w) {
        return new Document()
                .append("planId", w.getPlanId())
                .append("trainerId", w.getTrainerId())
                .append("planName", w.getPlanName())
                .append("planDetails", w.getPlanDetails());
    }

    private WorkoutPlan workoutFromDoc(Document d) {
        WorkoutPlan w = new WorkoutPlan();
        w.setPlanId(d.getString("planId"));
        w.setTrainerId(d.getString("trainerId"));
        w.setPlanName(d.getString("planName"));
        w.setPlanDetails(d.getString("planDetails"));
        return w;
    }

    private Document dietToDoc(DietPlan d) {
        return new Document()
                .append("dietId", d.getDietId())
                .append("trainerId", d.getTrainerId())
                .append("dietName", d.getDietName())
                .append("dietDetails", d.getDietDetails());
    }

    private DietPlan dietFromDoc(Document d) {
        DietPlan di = new DietPlan();
        di.setDietId(d.getString("dietId"));
        di.setTrainerId(d.getString("trainerId"));
        di.setDietName(d.getString("dietName"));
        di.setDietDetails(d.getString("dietDetails"));
        return di;
    }
}
