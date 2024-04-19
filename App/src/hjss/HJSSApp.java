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
        initializeData();// Initialize coaches and pre-registered learners
    }

    private void initializeData() {
        // Add coaches
        Coach coach1 = new Coach("John");
        Coach coach2 = new Coach("Emma");
        Coach coach3 = new Coach("Michael");
        coaches.add(coach1);
        coaches.add(coach2);
        coaches.add(coach3);

        // Add pre-registered learners
        Learner learner1 = new Learner("Alice", Gender.FEMALE, 8, "1234567890", 2);
        Learner learner2 = new Learner("Bob", Gender.MALE, 6, "9876543210", 1);
        learners.add(learner1);
        learners.add(learner2);

        // Initialize timetable with lessons
        timetable.addLesson(new Lesson(DayOfWeek.MONDAY, LocalTime.of(16, 0), LocalTime.of(17, 0), 1, coach1));
        timetable.addLesson(new Lesson(DayOfWeek.MONDAY, LocalTime.of(17, 0), LocalTime.of(18, 0), 2, coach2));
        // Add more lessons as needed
    }

    public static void main(String[] args) {
        HJSSApp app = new HJSSApp();
        app.runApp();
    }

    public void runApp() {
        boolean running = true;
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Book a lesson");
            System.out.println("2. Change/Cancel a booking");
            System.out.println("3. Attend a lesson");
            System.out.println("4. Generate learner report");
            System.out.println("5. Generate coach report");
            System.out.println("6. Register a new learner");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the learner's name:");
                    String learnerName = scanner.next();
                    Learner learner = getLearnerByName(learnerName);
                    if (learner != null) {
                        bookLesson(learner);
                    } else {
                        System.out.println("Learner not found.");
                    }
                    break;
                case 2:
                    System.out.println("Enter the learner's name:");
                    learnerName = scanner.next();
                    learner = getLearnerByName(learnerName);
                    if (learner != null) {
                        changeOrCancelBooking(learner);
                    } else {
                        System.out.println("Learner not found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the learner's name:");
                    learnerName = scanner.next();
                    learner = getLearnerByName(learnerName);
                    if (learner != null) {
                        attendLesson(learner);
                    } else {
                        System.out.println("Learner not found.");
                    }
                    break;
                case 4:
                    //generateLearnerReport();
                    break;
                case 5:
                    //generateCoachReport();
                    break;
                case 6:
                    //registerLearner();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
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
    public void changeOrCancelBooking(Learner learner) {
        System.out.println("1. Change a booking");
        System.out.println("2. Cancel a booking");
        int choice = scanner.nextInt();

        if (choice == 1) {
            changeBooking(learner);
        } else if (choice == 2) {
            cancelBooking(learner);
        } else {
            System.out.println("Invalid choice!");
        }
    }
    public void changeBooking(Learner learner) {
        System.out.println("Enter the booking ID to change:");
        int bookingId = scanner.nextInt();

        Booking booking = learner.getBookingById(bookingId);
        if (booking == null) {
            System.out.println("Invalid booking ID!");
            return;
        }

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

        System.out.println("Enter the lesson index to change the booking:");
        int lessonIndex = scanner.nextInt() - 1;

        if (lessonIndex < 0 || lessonIndex >= availableLessons.size()) {
            System.out.println("Invalid lesson index!");
            return;
        }

        Lesson newLesson = availableLessons.get(lessonIndex);
        if (learner.canBookLesson(newLesson) && !learner.hasBookedLesson(newLesson)) {
            timetable.changeBooking(booking, newLesson);
            System.out.println("Booking changed successfully!");
            System.out.println();
        } else {
            System.out.println("You cannot book this lesson due to grade level restrictions or you have already booked this lesson.");
        }
    }
    public void cancelBooking(Learner learner) {
        System.out.println("Enter the booking ID to cancel:");
        int bookingId = scanner.nextInt();

        Booking booking = learner.getBookingById(bookingId);
        if (booking == null) {
            System.out.println("Invalid booking ID!");
            return;
        }

        learner.cancelBooking(booking);
        System.out.println("Booking cancelled successfully!");
    }

    public void generateLearnerReport() {
        System.out.println("Enter the month number (1-12):");
        int month = scanner.nextInt();
        System.out.println("Monthly Learner Report for " + Month.of(month));
        for (Learner learner : learners) {
            System.out.println("Learner: " + learner.getName());
            System.out.println("Booked Lessons:");
            List<Booking> bookedLessons = learner.getBookings(BookingStatus.BOOKED);
            for (Booking booking : bookedLessons) {
                System.out.println(booking.getLesson());
            }
            System.out.println("Number of booked lessons: " + bookedLessons.size());
            System.out.println("Canceled Lessons:");
            List<Booking> canceledLessons = learner.getBookings(BookingStatus.CANCELED);
            for (Booking booking : canceledLessons) {
                System.out.println(booking.getLesson());
            }
            System.out.println("Number of canceled lessons: " + canceledLessons.size());

            System.out.println("Attended Lessons:");
            List<Booking> attendedLessons = learner.getBookings(BookingStatus.ATTENDED);
            for (Booking booking : attendedLessons) {
                System.out.println(booking.getLesson());
            }
            System.out.println("Number of attended lessons: " + attendedLessons.size());
            System.out.println();
        }
    }
    public void generateCoachReport() {
        System.out.println("Enter the month number (1-12):");
        int month = scanner.nextInt();

        System.out.println("Monthly Coach Report for " + Month.of(month));
        for (Coach coach : coaches) {
            System.out.println("Coach: " + coach.getName());
            double averageRating = coach.getAverageRating();
            System.out.println("Average Rating: " + averageRating);
            System.out.println();
        }
    }

    public void registerLearner() {
        System.out.println("Enter learner name:");
        String name = scanner.next();

        System.out.println("Enter learner gender (MALE/FEMALE):");
        Gender gender = Gender.valueOf(scanner.next().toUpperCase());

        System.out.println("Enter learner age:");
        int age = scanner.nextInt();

        System.out.println("Enter emergency contact number:");
        String emergencyContact = scanner.next();

        System.out.println("Enter current grade level (1-5):");
        int currentGrade = scanner.nextInt();

        if (age < 4 || age > 11) {
            System.out.println("Invalid age. Learners must be between 4 and 11 years old.");
            return;
        }

        Learner newLearner = new Learner(name, gender, age, emergencyContact, currentGrade);
        learners.add(newLearner);
        System.out.println("Learner registered successfully!");
    }

    public void attendLesson(Learner learner) {
        System.out.println("Enter the booking ID to attend the lesson:");
        int bookingId = scanner.nextInt();

        Booking booking = learner.getBookingById(bookingId);
        if (booking == null) {
            System.out.println("Invalid booking ID!");
            return;
        }

        if (booking.getStatus() == BookingStatus.ATTENDED) {
            System.out.println("You have already attended this lesson.");
            return;
        }

        learner.attendLesson(booking);
        learner.updateGradeLevel(booking.getLesson().getGrade());

        System.out.println("Enter your review for the lesson:");
        String review = scanner.next();

        System.out.println("Enter your rating (1-5):");
        int rating = scanner.nextInt();

        Review newReview = new Review(review, rating);
        learner.writeReview(booking.getLesson(), newReview);

        System.out.println("Lesson attended successfully!");
    }

    private Learner getLearnerByName(String name) {
        // Implement this method to retrieve the Learner object from the learners list based on the provided name
        // Return null if the learner is not found
        for (Learner learner : learners) {
            if (learner.getName().equalsIgnoreCase(name)) {
                return learner;
            }
        }
        return null;
    }


    // Implement other methods like changeBooking(), cancelBooking(), attendLesson(), generateLearnerReport(), generateCoachReport(), and registerLearner()

}