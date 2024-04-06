package hjss;

public class Booking {
    private Lesson lesson;
    private Learner learner;
    private BookingStatus status;

    public Booking(Lesson lesson, Learner learner) {
        this.lesson = lesson;
        this.learner = learner;
        this.status = BookingStatus.BOOKED;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    // Getters
}