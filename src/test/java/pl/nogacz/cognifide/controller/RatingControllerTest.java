package pl.nogacz.cognifide.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.nogacz.cognifide.library.Library;
import pl.nogacz.cognifide.library.dto.RatingDTO;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Dawid Nogacz on 14.06.2019
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("LibraryMock")
public class RatingControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private Library library;

    @Autowired
    private Gson gson;

    @Test
    public void getRating() {
        //Given
        List<RatingDTO> rating = new ArrayList<>();
        rating.add(new RatingDTO("author", 1.0));

        //When
        Mockito.when(library.getRating()).thenReturn(rating);

        //Then
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/rating", String.class))
                .contains(gson.toJson(new RatingDTO("author", 1.0)));
    }

    @Test
    public void getEmptyRating() {
        //Given
        List<RatingDTO> rating = new ArrayList<>();

        //When
        Mockito.when(library.getRating()).thenReturn(rating);

        //Then
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/rating", String.class))
                .contains("\"status\":404");
    }
}
