package pl.nogacz.cognifide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public void getCategory(@RequestParam("id") String id) throws Exception {

    }

    @RequestMapping(value = "/book/{id}")
    public void getCategory(@RequestParam("id") int id) throws Exception {

    }
}
