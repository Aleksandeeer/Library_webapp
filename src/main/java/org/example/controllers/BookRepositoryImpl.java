package org.example.controllers;

import org.example.components.HibernateUtil;
import org.example.models.Book;
import org.example.interfaces.BookRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private Session session;

    public BookRepositoryImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public List<Book> getAllBooks() {
        return session.createQuery("FROM Book", Book.class).list();
    }

    @Override
    public void addBook(Book book) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public Book findBookById(int id) {
        return session.get(Book.class, id);
    }

    @Override
    public void updateBook(Book book) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void deleteBook(int id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Book book = session.get(Book.class, id);
            if (book != null) session.delete(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}
