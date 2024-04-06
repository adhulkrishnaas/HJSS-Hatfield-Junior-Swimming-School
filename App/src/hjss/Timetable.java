package hjss;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    private List<Lesson> lessons;

    public Timetable() {
        lessons = new ArrayList<>();
        // Initialize the timetable with lessons
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public List<Lesson> getAvailableLessons(Day day) {
        List<Lesson> availableLessons = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getDay() == day && lesson.isAvailable()) {
                availableLessons.add(lesson);
            }
        }
        return availableLessons;
    }

    // Implement other methods like getAvailableLessons(Grade/Coach), bookLesson(), changeBooking(), and cancelBooking()

    public void bookLesson(Learner learner, Lesson lesson) {
        if (lesson.isAvailable() && learner.canBookLesson(lesson)) {
            Booking booking = new Booking(lesson, learner);
            lesson.addLearner(learner);
            learner.addBooking(booking);
        }
        // Handle booking constraints
    }
}
