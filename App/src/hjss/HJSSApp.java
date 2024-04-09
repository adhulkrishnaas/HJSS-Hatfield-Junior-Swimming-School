package hjss;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.Month;

public class HJSSApp {
    private static final Scanner scanner = new Scanner(System.in);
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

    public void bookLesson(Learner learner) {
        System.out.println("How would you like to view the timetable?");
        System.out.println("1. By Day");
        System.out.println("2. By Grade Level");
        System.out.println("3. By Coach Name");
        int choice = scanner.nextInt();

        List<Lesson> availableLessons;
        switch (choice) {
            case 1:
                System.out.println("Enter the day (MONDAY, WEDNESDAY, FRIDAY, SATURDAY):");
                DayOfWeek day = DayOfWeek.valueOf(scanner.next().toUpperCase());
                availableLessons = timetable.getAvailableLessons(day);
                break;
            case 2:
                System.out.println("Enter the grade level (1-5):");
                int grade = scanner.nextInt();
                availableLessons = timetable.getAvailableLessons(grade);
                break;
            case 3:
                System.out.println("Enter the coach name:");
                String coachName = scanner.next();
                availableLessons = timetable.getAvailableLessons(coachName);
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        if (availableLessons.isEmpty()) {
            System.out.println("No available lessons found.");
            return;
        }

        System.out.println("Available Lessons:");
        for (int i = 0; i < availableLessons.size(); i++) {
            System.out.println((i + 1) + ". " + availableLessons.get(i));
        }

        System.out.println("Enter the lesson index to book:");
        int lessonIndex = scanner.nextInt() - 1;

        if (lessonIndex < 0 || lessonIndex >= availableLessons.size()) {
            System.out.println("Invalid lesson index!");
            return;
        }

        //Lesson selectedLesson = availableLessons.get(lessonIndex);

        /*if (learner.canBookLesson(selectedLesson) && !learner.hasBookedLesson(selectedLesson)) {
            timetable.bookLesson(learner, selectedLesson);
            System.out.println("Lesson booked successfully!");

        }*/
        Lesson selectedLesson = availableLessons.get(lessonIndex);
        Booking newBooking = timetable.bookLesson(learner, selectedLesson);
        if (newBooking != null) {
            System.out.println("Lesson booked successfully! Booking ID: " + newBooking.getId());
        }
        else {
            System.out.println("You cannot book this lesson due to grade level restrictions or you have already booked this lesson.");
        }
    }

    // Implement other methods like changeBooking(), cancelBooking(), attendLesson(), generateLearnerReport(), generateCoachReport(), and registerLearner()

}