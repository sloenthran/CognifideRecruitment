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
public class BookCategoryControllerTest {
    @LocalServerPort
    private int port;

    private static final String LOCALHOST = "http://localhost:";

    @Autowired
    private Library library;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getBooksFromCategory() {
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
        Mockito.when(library.getBooksFromCategory("test")).thenReturn(bookSet);

        //Then
        assertThat(this.restTemplate.getForObject(LOCALHOST + port + "/getBookFromCategory/test", String.class))
                .contains(new Gson().toJson(bookSet));
    }

    @Test
    public void getEmptyBooksFromCategory() {
        //Given
        Set<Book> bookSet = new HashSet<>();

        //When
        Mockito.when(library.getBooksFromCategory("test")).thenReturn(bookSet);

        //Then
        assertThat(this.restTemplate.getForObject(LOCALHOST + port + "/getBookFromCategory/test", String.class))
                .contains("\"status\":404");
    }

    @Test
    public void getCategory() {
        //Given
        Set<String> category = new HashSet<>();

        category.add("a");
        category.add("b");
        category.add("c");

        //When
        Mockito.when(library.getCategory()).thenReturn(category);

        //Then
        assertThat(this.restTemplate.getForObject(LOCALHOST + port + "/getCategory", String.class))
                .contains(new Gson().toJson(category));
    }

    @Test
    public void getEmptyCategory() {
        //Given
        Set<String> category = new HashSet<>();

        //When
        Mockito.when(library.getCategory()).thenReturn(category);

        //Then
        assertThat(this.restTemplate.getForObject(LOCALHOST + port + "/getCategory", String.class))
                .contains("\"status\":404");
    }
}