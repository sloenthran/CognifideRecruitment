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
import pl.nogacz.cognifide.library.Book;
import pl.nogacz.cognifide.library.Library;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dawid Nogacz on 14.06.2019
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("LibraryMock")
public class BookDetailsControllerTest {
    @LocalServerPort
    private int port;

    private static final String LOCALHOST = "http://localhost:";

    @Autowired
    private Library library;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getBook() {
        //Given
        Book book = new Book.Builder()
                .isbn("123")
                .language("pl")
                .build();

        //When
        Mockito.when(library.getBook("123")).thenReturn(book);

        //Then
        assertThat(this.restTemplate.getForObject(LOCALHOST + port + "/getBook/123", String.class))
                .contains(new Gson().toJson(book));
    }

    @Test
    public void getEmptyBook() {
        //Given

        //When
        Mockito.when(library.getBook("123")).thenReturn(null);

        //Then
        assertThat(this.restTemplate.getForObject(LOCALHOST + port + "/getBook/123", String.class))
                .contains("\"status\":404");
    }

    @Test
    public void getBooks() {
        //Given
        Set<Book> bookSet = new HashSet<>();

        bookSet.add(new Book
                .Builder()
                .isbn("1")
                .pageCount(1)
                .build());

        bookSet.add(new Book
                .Builder()
                .isbn("2")
                .pageCount(1)
                .build());

        //When
        Mockito.when(library.getBooks()).thenReturn(bookSet);

        //Then
        assertThat(this.restTemplate.getForObject(LOCALHOST + port + "/getBooks", String.class))
                .contains(new Gson().toJson(bookSet));
    }

    @Test
    public void getEmptyBooks() {
        //Given
        Set<Book> bookSet = new HashSet<>();

        //When
        Mockito.when(library.getBooks()).thenReturn(bookSet);

        //Then
        assertThat(this.restTemplate.getForObject(LOCALHOST + port + "/getBooks", String.class))
                .contains("\"status\":404");
    }
}