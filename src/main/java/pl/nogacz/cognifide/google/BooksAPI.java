package pl.nogacz.cognifide.google;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Dawid Nogacz on 12.06.2019
 */
@Component
public class BooksAPI {
    private RestTemplate restTemplate = new RestTemplate();

    public String getBooks() {
        String queryUrl = "https://www.googleapis.com/books/v1/volumes?q=java";
        String json = null;

        do {
            json = restTemplate.getForObject(queryUrl, String.class);
        } while(json == null);

        return json;
    }
}
