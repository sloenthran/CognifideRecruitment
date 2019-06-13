package pl.nogacz.cognifide.controller;

import org.springframework.web.bind.annotation.*;
import pl.nogacz.cognifide.library.Library;

/**
 * @author Dawid Nogacz on 12.06.2019
 */
@RestController
@CrossOrigin(origins = "*")
public class BookDetailsController {
    @RequestMapping(value = "/book/{id}")
    public void getCategory(@PathVariable("id") String id) throws Exception {
        Library.getInstance();
    }
}
