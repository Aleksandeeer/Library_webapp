package org.example.interfaces;

import org.example.models.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();
    void addBook(Book book);
    Book findBookById(int id);
    void updateBook(Book book);
    void deleteBook(int id);
}
