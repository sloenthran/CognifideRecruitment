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
import pl.nogacz.cognifide.library.Book;
import pl.nogacz.cognifide.library.Library;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * @author Dawid Nogacz on 14.06.2019
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PowerMockIgnore({"javax.management.*"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PrepareForTest(Library.class)
public class BookCategoryControllerTest {
    @LocalServerPort
    private int port;

    private static final String LOCALHOST = "http://localhost:";

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void getBooksFromCategory() {
        //Given
        PowerMockito.mockStatic(Library.class);
        Library library = Mockito.mock(Library.class);

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
        PowerMockito.when(Library.getInstance()).thenReturn(library);
        PowerMockito.when(Library.getInstance().getBooksFromCategory("test")).thenReturn(bookSet);

        //Then
        assertThat(this.restTemplate.getForObject(LOCALHOST + port + "/getBookFromCategory/test", String.class))
                .contains(new Gson().toJson(bookSet));
    }

    @Test
    public void getEmptyBooksFromCategory() {
        //Given
        PowerMockito.mockStatic(Library.class);
        Library library = Mockito.mock(Library.class);

        Set<Book> bookSet = new HashSet<>();

        //When
        PowerMockito.when(Library.getInstance()).thenReturn(library);
        PowerMockito.when(Library.getInstance().getBooksFromCategory("test")).thenReturn(bookSet);

        //Then
        assertThat(this.restTemplate.getForObject(LOCALHOST + port + "/getBookFromCategory/test", String.class))
                .contains("\"status\":404");
    }

    @Test
    public void getCategory() {
        //Given
        PowerMockito.mockStatic(Library.class);
        Library library = Mockito.mock(Library.class);

        Set<String> category = new HashSet<>();

        category.add("a");
        category.add("b");
        category.add("c");

        //When
        PowerMockito.when(Library.getInstance()).thenReturn(library);
        PowerMockito.when(Library.getInstance().getCategory()).thenReturn(category);

        //Then
        assertThat(this.restTemplate.getForObject(LOCALHOST + port + "/getCategory", String.class))
                .contains(new Gson().toJson(category));
    }

    @Test
    public void getEmptyCategory() {
        //Given
        PowerMockito.mockStatic(Library.class);
        Library library = Mockito.mock(Library.class);

        Set<String> category = new HashSet<>();

        //When
        PowerMockito.when(Library.getInstance()).thenReturn(library);
        PowerMockito.when(Library.getInstance().getCategory()).thenReturn(category);

        //Then
        assertThat(this.restTemplate.getForObject(LOCALHOST + port + "/getCategory", String.class))
                .contains("\"status\":404");
    }
}