package hjss;

import java.util.ArrayList;
import java.util.List;

public class Coach {
    private String name;
    private List<Lesson> lessons;
    private List<Review> reviews;

    public Coach(String name) {
        this.name = name;
        this.lessons = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) {
            return 0.0;
        }

        double totalRating = 0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }

        return totalRating / reviews.size();
    }

    // Getters and setters
}
