package pl.nogacz.cognifide.library.dto;

/**
 * @author Dawid Nogacz on 13.06.2019
 */
public class RatingDTO implements Comparable {
    private String author;
    private double averageRating;

    public RatingDTO(String author, double averageRating) {
        this.author = author;
        this.averageRating = averageRating;
    }

    public String getAuthor() {
        return author;
    }

    public double getAverageRating() {
        return averageRating;
    }

    @Override
    public int compareTo(Object object) {
        RatingDTO compareObject = (RatingDTO) object;
        return Double.compare(compareObject.averageRating, averageRating);
    }
}
