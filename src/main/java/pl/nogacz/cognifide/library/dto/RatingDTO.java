package pl.nogacz.cognifide.library.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingDTO ratingDTO = (RatingDTO) o;
        return Double.compare(ratingDTO.averageRating, averageRating) == 0 &&
                Objects.equals(author, ratingDTO.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, averageRating);
    }
}
