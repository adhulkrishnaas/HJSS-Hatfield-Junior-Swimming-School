package hjss;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Learner {
    private String name;
    private Gender gender;
    private int age;
    private String emergencyContact;
    private int currentGrade;
    private List<Booking> bookings;
    private List<Review> reviews;

    public Learner(String name, Gender gender, int age, String emergencyContact, int currentGrade) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.currentGrade = currentGrade;
        this.bookings = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public boolean canBookLesson(Lesson lesson) {
        int lessonGrade = lesson.getGrade();
        return lessonGrade <= currentGrade + 1 && lessonGrade >= currentGrade;
    }

    public boolean hasBookedLesson(Lesson lesson) {
        return bookings.stream()
                .anyMatch(booking -> booking.getLesson().equals(lesson));
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public Booking getBookingById(int bookingId) {
        return bookings.stream()
                .filter(booking -> booking.getId() == bookingId)
                .findFirst()
                .orElse(null);
    }

    public void changeBooking(Booking booking, Lesson newLesson) {
        if (canBookLesson(newLesson) && !hasBookedLesson(newLesson)) {
            booking.getLesson().removeLearner(this);
            booking.setLesson(newLesson);
            newLesson.addLearner(this);
        } else {
            System.out.println("You cannot book this lesson due to grade level restrictions or you have already booked this lesson.");
        }
    }

    public void cancelBooking(Booking booking) {
        bookings.remove(booking);
        booking.getLesson().removeLearner(this);
    }

    public void attendLesson(Booking booking) {
        booking.setStatus(BookingStatus.ATTENDED);
    }

    public void updateGradeLevel(int attendedGrade) {
        if (attendedGrade > currentGrade) {
            currentGrade = attendedGrade;
            System.out.println("Your current grade level has been updated to " + currentGrade);
        }
    }

    public void writeReview(Lesson lesson, Review review) {
        reviews.add(review);
        lesson.getCoach().addReview(review);
    }

    public List<Booking> getBookings(BookingStatus status) {
        return bookings.stream()
                .filter(booking -> booking.getStatus() == status)
                .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    // Getters and setters for other attributes
}

    // Getters and setters
}