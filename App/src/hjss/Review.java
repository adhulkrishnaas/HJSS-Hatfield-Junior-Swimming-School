package hjss;

public class Review {
    private String description;
    private int rating;

    public Review(String description, int rating) {
        this.description = description;
        this.rating = rating;
    }
    public int getRating() {
        return rating;
    }

    // Getters and setters
}
