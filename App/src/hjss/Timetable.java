package hjss;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Timetable {
    private List<Lesson> lessons;

    public Timetable() {
        lessons = new ArrayList<>();
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public List<Lesson> getAvailableLessons(DayOfWeek day) {
        return lessons.stream()
                .filter(lesson -> lesson.getDay() == day && lesson.isAvailable())
                .collect(Collectors.toList());
    }

    public List<Lesson> getAvailableLessons(int grade) {
        return lessons.stream()
                .filter(lesson -> lesson.getGrade() <= grade + 1 && lesson.isAvailable())
                .collect(Collectors.toList());
    }

    public List<Lesson> getAvailableLessons(String coachName) {
        return lessons.stream()
                .filter(lesson -> lesson.getCoach().getName().equalsIgnoreCase(coachName) && lesson.isAvailable())
                .collect(Collectors.toList());
    }

   /* public void bookLesson(Learner learner, Lesson lesson) {
        if (lesson.isAvailable() && learner.canBookLesson(lesson) && !learner.hasBookedLesson(lesson)) {
            Booking booking = new Booking(lesson, learner);
            lesson.addLearner(learner);
            learner.addBooking(booking);
            //System.out.println("Lesson booked successfully!");
        } else {
            System.out.println("The lesson is already full, you cannot book this lesson due to grade level restrictions, or you have already booked this lesson.");

        }
    }*/

    public Booking bookLesson(Learner learner, Lesson lesson) {
        if (lesson.isAvailable() && learner.canBookLesson(lesson) && !learner.hasBookedLesson(lesson)) {
            Booking booking = new Booking(lesson, learner);
            lesson.addLearner(learner);
            learner.addBooking(booking);
            System.out.println("Lesson booked successfully!");
            return booking;
        } else {
            System.out.println("The lesson is already full, you cannot book this lesson due to grade level restrictions, or you have already booked this lesson.");
            return null;
        }
    }

    public void changeBooking(Booking booking, Lesson newLesson) {
        if (newLesson.isAvailable()) {
            Lesson oldLesson = booking.getLesson();
            oldLesson.removeLearner(booking.getLearner());
            newLesson.addLearner(booking.getLearner());
            booking.setLesson(newLesson);
        } else {
            System.out.println("The new lesson is already full.");
        }
    }
}
