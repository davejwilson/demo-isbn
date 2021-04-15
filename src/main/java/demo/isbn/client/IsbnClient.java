package demo.isbn.client;

import demo.isbn.model.Book;
import feign.Feign;
import feign.Logger;
import feign.Param;
import feign.RequestLine;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

import java.util.Map;

public class IsbnClient {
    private BookClient bookClient;

    public IsbnClient() {
        bookClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(BookClient.class))
                .logLevel(Logger.Level.FULL)
                .target(BookClient.class, "http://openlibrary.org");
    }

    public Book fetchBookInfo(String isbn) {
        Map<String, Book> book = bookClient.findByIsbn(isbn);
        System.out.println(book);
        return book.get(isbn);
    }

    public static void main(String[] args) {
        IsbnClient client = new IsbnClient();
        Book book = client.fetchBookInfo("ISBN:1931498717");
        System.out.println(book);
    }
}

interface BookClient {
    @RequestLine("GET /api/books?bibkeys={isbn}&jscmd=details&format=json")
    Map<String, Book> findByIsbn(@Param("isbn") String isbn);
}