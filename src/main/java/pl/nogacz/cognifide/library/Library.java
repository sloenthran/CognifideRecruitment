package pl.nogacz.cognifide.library;

import com.google.gson.Gson;
import pl.nogacz.cognifide.google.BooksAPI;
import pl.nogacz.cognifide.library.json.map.BookJson;

import java.util.HashSet;
import java.util.Set;

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
        if(instance == null) {
            synchronized(Library.class) {
                if(instance == null) {
                    instance = new Library();
                }
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
}
