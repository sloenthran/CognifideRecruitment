package pl.nogacz.cognifide.library;

import com.google.gson.Gson;
import pl.nogacz.cognifide.google.BooksAPI;
import pl.nogacz.cognifide.library.dto.RatingDTO;
import pl.nogacz.cognifide.library.json.map.BookJson;

import java.util.*;

/**
 * @author Dawid Nogacz on 12.06.2019
 */
public class Library {
    private static Library instance = null;
    private Set<Book> books;

    private Library() {
        updateLibrary();
    }

    public static Library getInstance() {
        synchronized(Library.class) {
            if(instance == null) {
                instance = new Library();
            }
        }

        return instance;
    }

    private void updateLibrary() {
        BooksAPI booksAPI = new BooksAPI();
        Gson gson = new Gson();

        BookJson bookJson = gson.fromJson(booksAPI.getBooks(), BookJson.class);
        books = new HashSet<>(bookJson.getBooks());
    }

    public Book getBook(String isbn) {
        for(Book book : books) {
            if(book.getIsbn().contains(isbn)) {
                return book;
            }
        }

        return null;
    }

    public Set<Book> getBooksFromCategory(String category) {
        Set<Book> bookSet = new HashSet<>();

        for(Book book : books) {
            if(book.getCategories() != null && book.getCategories().contains(category)) {
               bookSet.add(book);
            }
        }

        return bookSet;
    }

    public List<RatingDTO> getRating() {
        Set<String> authors = new HashSet<>();

        for(Book book : books) {
            if(book.getAuthors() != null) {
                authors.addAll(book.getAuthors());
            }
        }

        List<RatingDTO> rating = new ArrayList<>();

        for(String author : authors) {
            int count = 0;
            double average = 0;
            for(Book book : books) {
                if(book.getAverageRating() != null && book.getAuthors() != null && book.getAuthors().contains(author)) {
                    count++;
                    average += book.getAverageRating();
                }
            }

            if(count > 0) {
                rating.add(new RatingDTO(author, average / count));
            }
        }

        Collections.sort(rating);

        return rating;
    }

    public Set<String> getCategory() {
        Set<String> category = new HashSet<>();

        for(Book book : books) {
            if(book.getCategories() != null && !book.getCategories().isEmpty()) {
                category.addAll(book.getCategories());
            }
        }

        return category;
    }
}
