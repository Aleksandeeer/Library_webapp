package org.example.services;

import lombok.Getter;
import org.example.components.HibernateUtil;
import org.example.models.Book;
import org.example.models.Library_worker;
import org.example.models.Loan;
import org.example.models.Member;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@org.springframework.stereotype.Service
@Getter
public class Service {
    List<Book> books;
    List<Member> members;
    List<Library_worker> library_workers;
    List<Loan> loans;
    int role = 0; // ? 0 - Гость, 1 - Читатель, 2 - Работник

    Session session;

    public Service() {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            // Выгрузки данных из таблиц
            books = session.createQuery("FROM Book", Book.class).list();
            members = session.createQuery("FROM Member", Member.class).list();
            library_workers = session.createQuery("FROM Library_worker", Library_worker.class).list();
            loans = session.createQuery("FROM Loan", Loan.class).list();
        } catch (Exception e) {
            System.out.println("Ошибка при обработке данных таблицы.");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void addBook(Book book) {
        books.add(book);

        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
