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
        String queryUrl = "https://www.googleapis.com/books/v1/volumes?q=java&maxResults=40";
        String json = null;

        //I used here the do-while loop because I did not generate the key and the Google API sometimes throws 503 error on me
        do {
            try {
                json = restTemplate.getForObject(queryUrl, String.class);
            } catch(Exception e) {
                json = null;
            }
        } while(json == null);

        return json;
    }
}