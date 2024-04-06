package hjss;

import java.util.ArrayList;
import java.util.List;

public class HJSSApp {
    private List<Learner> learners;
    private List<Coach> coaches;
    private Timetable timetable;

    public HJSSApp() {
        learners = new ArrayList<>();
        coaches = new ArrayList<>();
        timetable = new Timetable();
        // Initialize coaches and pre-registered learners
    }

    public void main() {
        // Implement the main program logic and user interface
    }

    /*public void bookLesson(Learner learner, Lesson lesson) {
        timetable.bookLesson(learner, lesson);
    }
     */

    // Implement other methods like changeBooking(), cancelBooking(), attendLesson(), generateLearnerReport(), generateCoachReport(), and registerLearner()
}