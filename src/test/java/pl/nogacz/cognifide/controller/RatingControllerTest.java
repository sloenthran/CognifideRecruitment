package pl.nogacz.cognifide.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import pl.nogacz.cognifide.library.Library;
import pl.nogacz.cognifide.library.dto.RatingDTO;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Dawid Nogacz on 14.06.2019
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PowerMockIgnore({"javax.management.*"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PrepareForTest(Library.class)
public class RatingControllerTest {
    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void getRating() {
        //Given
        PowerMockito.mockStatic(Library.class);
        Library library = Mockito.mock(Library.class);

        List<RatingDTO> rating = new ArrayList<>();
        rating.add(new RatingDTO("author", 1.0));

        //When
        PowerMockito.when(Library.getInstance()).thenReturn(library);
        PowerMockito.when(Library.getInstance().getRating()).thenReturn(rating);

        //Then
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/rating", String.class))
                .contains(new Gson().toJson(new RatingDTO("author", 1.0)));
    }

    @Test
    public void getEmptyRating() {
        //Given
        PowerMockito.mockStatic(Library.class);
        Library library = Mockito.mock(Library.class);

        List<RatingDTO> rating = new ArrayList<>();

        //When
        PowerMockito.when(Library.getInstance()).thenReturn(library);
        PowerMockito.when(Library.getInstance().getRating()).thenReturn(rating);

        //Then
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/rating", String.class))
                .contains("\"status\":404");
    }
}