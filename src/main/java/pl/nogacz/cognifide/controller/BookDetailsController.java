package pl.nogacz.cognifide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.nogacz.cognifide.google.BooksAPI;

/**
 * @author Dawid Nogacz on 12.06.2019
 */
@RestController
@CrossOrigin(origins = "*")
public class BookDetailsController {
    @Autowired
    private BooksAPI booksAPI;

    @RequestMapping(value = "/book/{id}")
    public void getCategory(@PathVariable("id") String id) throws Exception {
        System.out.println(booksAPI.getBooks());
    }
}
