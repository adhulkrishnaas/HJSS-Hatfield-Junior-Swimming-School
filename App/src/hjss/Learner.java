package hjss;

import java.util.ArrayList;
import java.util.List;

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
        return lesson.getGrade() <= currentGrade + 1;
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
        // Implement logic to change the booking
    }

    public void cancelBooking(Booking booking) {
        bookings.remove(booking);
        booking.getLesson().removeLearner(this);
    }

    public void attendLesson(Booking booking) {
        booking.setStatus(BookingStatus.ATTENDED);
        // Update the learner's current grade if needed
    }

    public void writeReview(Lesson lesson, Review review) {
        reviews.add(review);
        lesson.getCoach().addReview(review);
    }

    // Getters and setters
}