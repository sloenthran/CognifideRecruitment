package pl.nogacz.cognifide.controller;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.nogacz.cognifide.library.Book;
import pl.nogacz.cognifide.library.Library;

import java.util.Set;

/**
 * @author Dawid Nogacz on 12.06.2019
 */
@RestController
@CrossOrigin(origins = "*")
public class BookCategoryController {
    @RequestMapping(value = "/bookCategory/{category}", produces = "application/json")
    public ResponseEntity<String> getCategory(@PathVariable("category") String category) {
        Set<Book> bookSet = Library.getInstance().getBooksFromCategory(category);

        if(!bookSet.isEmpty()) {
            return new ResponseEntity<>(new Gson().toJson(bookSet), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Category not found!"
            );
        }
    }
}
