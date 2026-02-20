package bookmyshow.models;

import java.util.UUID;

public class Movie {

    private final String id;
    private final String title;
    private final String description;
    private final int durationMinutes;

    public Movie(String title, String description, int durationMinutes) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
    }

    public Movie(String id, String title, String description, int durationMinutes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.durationMinutes = durationMinutes;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }
}
