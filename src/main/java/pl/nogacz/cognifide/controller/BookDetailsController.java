package pl.nogacz.cognifide.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.nogacz.cognifide.library.Book;
import pl.nogacz.cognifide.library.Library;

import java.util.Set;

/**
 * @author Dawid Nogacz on 12.06.2019
 */
@RestController
@CrossOrigin(origins = "*")
public class BookDetailsController {
    @Autowired
    private Library library;

    @GetMapping(value = "/book/{id}", produces = "application/json")
    public ResponseEntity<String> getBook(@PathVariable("id") String id) {
        Book book = library.getBook(id);

        if(book != null) {
            return new ResponseEntity<>(new Gson().toJson(book), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Wrong ID!"
            );
        }
    }

    @GetMapping(value = "/books", produces = "application/json")
    public ResponseEntity<String> getBooks() {
        Set<Book> books = library.getBooks();

        if(!books.isEmpty()) {
            return new ResponseEntity<>(new Gson().toJson(books), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Library don't have any book!"
            );
        }
    }
}
