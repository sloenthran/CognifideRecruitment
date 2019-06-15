package pl.nogacz.cognifide.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.nogacz.cognifide.library.Library;
import pl.nogacz.cognifide.library.dto.RatingDTO;

import java.util.List;

/**
 * @author Dawid Nogacz on 12.06.2019
 */
@RestController
@CrossOrigin(origins = "*")
public class RatingController {
    @Autowired
    private Library library;

    @GetMapping(value = "/rating", produces = "application/json")
    public ResponseEntity<String> getRating() {
        List<RatingDTO> rating = library.getRating();

        if(!rating.isEmpty()) {
            return new ResponseEntity<>(new Gson().toJson(rating), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Rating is null!"
            );
        }
    }
}