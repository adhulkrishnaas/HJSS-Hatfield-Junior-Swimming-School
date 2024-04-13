package hjss;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

enum BookingStatus {
    BOOKED, CANCELED, ATTENDED
}


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
        coach.addLesson(this);
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

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getGrade() {
        return grade;
    }

    public Coach getCoach() {
        return coach;
    }

    public List<Learner> getBookedLearners() {
        return bookedLearners;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "day=" + day +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", grade=" + grade +
                ", coach=" + coach.getName() +
                ", bookedLearners=" + bookedLearners.size() + "/4" +
                '}';
    }
}