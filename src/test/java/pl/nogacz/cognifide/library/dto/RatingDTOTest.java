package pl.nogacz.cognifide.library.dto;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Dawid Nogacz on 16.06.2019
 */
public class RatingDTOTest {
    @Test
    public void averageSort() {
        //Given
        List<RatingDTO> rating = new ArrayList<>();

        rating.add(new RatingDTO("1", 3.0));
        rating.add(new RatingDTO("2", 0.5));
        rating.add(new RatingDTO("3", 4.0));
        rating.add(new RatingDTO("4", 6.0));
        rating.add(new RatingDTO("5", 1.0));

        //When
        Collections.sort(rating);

        //Then
        Assert.assertEquals(6.0, rating.get(0).getAverageRating(), 0.1);
        Assert.assertEquals(0.5, rating.get(4).getAverageRating(), 0.1);
    }
}