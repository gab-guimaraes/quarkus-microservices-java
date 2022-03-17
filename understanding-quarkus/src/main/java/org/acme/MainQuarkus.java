package org.acme;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.acme.service.BookService;
import org.acme.service.LegacyBookService;

import javax.inject.Inject;

@QuarkusMain
public class MainQuarkus implements QuarkusApplication {

    @Inject
    BookService bookService;

    @Inject
    LegacyBookService legacyBookService;

    @Override
    public int run(String... args) throws Exception {
        Book book = bookService.createBook("harry potter", 10.0f, "a history about magic");
        Book book2 = legacyBookService.createBook("twilight saga", 10.0f, "love of human and vampire");
        System.out.println(book.toString());
        System.out.println(book2.toString());
        return 0;
    }
}
