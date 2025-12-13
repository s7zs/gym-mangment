package services;
import org.example.repo.TrainerRepository;
import org.example.model.*;
import java.util.List;

public class TrainerService {

    private final TrainerRepository repo;

    public TrainerService(TrainerRepository repo) {
        this.repo = repo;
    }

    // -------- WORKOUTS --------
    public void createWorkout(WorkoutPlan w) {
        repo.saveWorkout(w);
    }

    public void updateWorkout(WorkoutPlan w) {
        repo.updateWorkout(w);
    }

    public List<WorkoutPlan> getAllWorkoutPlans() {
        return repo.findAllWorkouts();
    }

    public WorkoutPlan findWorkoutByName(String name) {
        return repo.findWorkoutByName(name);
    }

    // -------- DIETS --------
    public void createDiet(DietPlan d) {
        repo.saveDiet(d);
    }

    public void updateDiet(DietPlan d) {
        repo.updateDiet(d);
    }

    public List<DietPlan> getAllDietPlans() {
        return repo.findAllDiets();
    }

    public DietPlan findDietByName(String name) {
        return repo.findDietByName(name);
    }
}
