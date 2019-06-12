package pl.nogacz.cognifide.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dawid Nogacz on 12.06.2019
 */
@RestController
@CrossOrigin(origins = "*")
public class BookCategoryController {
    @RequestMapping(value = "/bookCategory/{category}")
    public void getCategory(@RequestParam("category") String category) throws Exception {

    }
}
