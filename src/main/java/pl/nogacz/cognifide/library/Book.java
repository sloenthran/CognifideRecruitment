package pl.nogacz.cognifide.library;

import java.util.*;

/**
 * @author Dawid Nogacz on 12.06.2019
 */
public class Book {
    private String id;
    private String isbn;
    private String title;
    private String subtitle;
    private String publisher;
    private long publishedDate;
    private String description;
    private int pageCount;
    private String thumbnailUrl;
    private String language;
    private String previewLink;
    private double averageRating;
    private List<String> authors;
    private List<String> categories;

    public Book(Builder builder) {
        this.id = builder.id;
        this.isbn = builder.isbn;
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.publisher = builder.publisher;
        this.publishedDate = builder.publishedDate;
        this.description = builder.description;
        this.pageCount = builder.pageCount;
        this.thumbnailUrl = builder.thumbnailUrl;
        this.language = builder.language;
        this.previewLink = builder.previewLink;
        this.averageRating = builder.averageRating;
        this.authors = builder.authors;
        this.categories = builder.categories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public long getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(long publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }

    public static class Builder {
        private String id;
        private String isbn;
        private String title;
        private String subtitle;
        private String publisher;
        private long publishedDate;
        private String description;
        private int pageCount;
        private String thumbnailUrl;
        private String language;
        private String previewLink;
        private double averageRating;
        private List<String> authors;
        private List<String> categories;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder subtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder publishedDate(long publishedDate) {
            this.publishedDate = publishedDate;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder pageCount(int pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Builder thumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder previewLink(String previewLink) {
            this.previewLink = previewLink;
            return this;
        }

        public Builder averageRating(double averageRating) {
            this.averageRating = averageRating;
            return this;
        }

        public Builder authors(List<String> authors) {
            this.authors = authors;
            return this;
        }

        public Builder categories(List<String> categories) {
            this.categories = categories;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
