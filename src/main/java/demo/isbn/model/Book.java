package demo.isbn.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class Book {
    @JsonAlias({"bib_key"})
    private String isbn;

    private BookDetails details;
}
