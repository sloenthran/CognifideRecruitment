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

        for(Items item : items) {
            books.add(createBook(item));
        }

        return books;
    }

    private String getIsbn(Items item) {
        String isbn = item.getVolumeInfo().getIndustryIdentifiers().stream()
                .filter(entry -> entry.getType().contains("ISBN_13"))
                .map(entry -> entry.getIdentifier())
                .limit(1)
                .collect(Collectors.joining());

        if(isbn == null || isbn.isEmpty()) {
            isbn = item.getId();
        }

        return isbn;
    }

    private Book createBook(Items item) {
        DateToLong dateToLong = new DateToLong();

        VolumeInfo volumeInfo = item.getVolumeInfo();

        Long publishedDate = null;

        if(volumeInfo.getPublishedDate() != null) {
            publishedDate = dateToLong.convert(volumeInfo.getPublishedDate());
        }

        return new Book
                .Builder()
                .isbn(getIsbn(item))
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
    }
}
