package hjss;

import java.util.concurrent.atomic.AtomicInteger;

public class Booking {
    private static final AtomicInteger idGenerator = new AtomicInteger(1);
    private final int id;
    private Lesson lesson;
    private Learner learner;
    private BookingStatus status;

    public Booking(Lesson lesson, Learner learner) {
        this.id = idGenerator.getAndIncrement();
        this.lesson = lesson;
        this.learner = learner;
        this.status = BookingStatus.BOOKED;
    }

    public int getId() {
        return id;
    }


    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Learner getLearner() {
        return learner;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}