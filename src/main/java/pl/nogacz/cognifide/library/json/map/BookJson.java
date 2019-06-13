package pl.nogacz.cognifide.library.json.map;

import pl.nogacz.cognifide.library.Book;
import pl.nogacz.cognifide.util.DateToLong;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Dawid Nogacz on 13.06.2019
 */
public class BookJson {
    private List<Items> items;

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public Set<Book> getBooks() {
        Set<Book> books = new HashSet<>();
        DateToLong dateToLong = new DateToLong();

        for(Items item : items) {
            String isbn = item.getVolumeInfo().getIndustryIdentifiers().stream()
                    .filter(entry -> entry.getType().contains("ISBN_13"))
                    .map(entry -> entry.getIdentifier())
                    .limit(1)
                    .collect(Collectors.joining());

            if(isbn == null || isbn.isEmpty()) {
                isbn = item.getId();
            }

            VolumeInfo volumeInfo = item.getVolumeInfo();

            Long publishedDate = null;

            if(volumeInfo.getPublishedDate() != null) {
                publishedDate = dateToLong.convert(volumeInfo.getPublishedDate());
            }

            Book book = new Book
                    .Builder()
                    .isbn(isbn)
                    .title(volumeInfo.getTitle())
                    .subtitle(volumeInfo.getSubtitle())
                    .publisher(volumeInfo.getPublisher())
                    .publishedDate(publishedDate)
                    .description(volumeInfo.getDescription())
                    .pageCount(volumeInfo.getPageCount())
                    .thumbnailUrl(volumeInfo.getImageLinks().getThumbnail())
                    .language(volumeInfo.getLanguage())
                    .previewLink(volumeInfo.getPreviewLink())
                    .averageRating(volumeInfo.getAverageRating())
                    .authors(volumeInfo.getAuthors())
                    .categories(volumeInfo.getCategories())
                    .build();

            books.add(book);
        }

        return books;
    }
}
