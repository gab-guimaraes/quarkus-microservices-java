package org.acme.service;

import org.acme.Book;
import org.acme.NumberGenerator;
import org.acme.qualifiers.EightDigits;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class LegacyBookService {

    @Inject
    @EightDigits
    NumberGenerator numberGenerator;

    public Book createBook(String title, Float price, String description) {
        Book book = new Book(title, price, description);
        book.setIsbn(numberGenerator.generateNumber());
        return book;
    }
}
