package pl.nogacz.cognifide.controller;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.nogacz.cognifide.library.Book;
import pl.nogacz.cognifide.library.Library;

/**
 * @author Dawid Nogacz on 12.06.2019
 */
@RestController
@CrossOrigin(origins = "*")
public class BookDetailsController {
    @RequestMapping(value = "/book/{id}", produces = "application/json")
    public ResponseEntity<?> getCategory(@PathVariable("id") String id) throws Exception {
        Book book = Library.getInstance().getBook(id);

        if(book != null) {
            return new ResponseEntity<>(new Gson().toJson(book), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Wrong ID!"
            );
        }
    }
}
