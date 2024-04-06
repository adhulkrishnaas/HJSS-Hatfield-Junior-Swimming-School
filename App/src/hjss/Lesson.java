package hjss;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private int grade;
    private Coach coach;
    private List<Learner> bookedLearners;

    public Lesson(DayOfWeek day, LocalTime startTime, LocalTime endTime, int grade, Coach coach) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.grade = grade;
        this.coach = coach;
        this.bookedLearners = new ArrayList<>();
    }

    public boolean isAvailable() {
        return bookedLearners.size() < 4;
    }

    public void addLearner(Learner learner) {
        bookedLearners.add(learner);
    }

    public void removeLearner(Learner learner) {
        bookedLearners.remove(learner);
    }

    // Getters and setters
}