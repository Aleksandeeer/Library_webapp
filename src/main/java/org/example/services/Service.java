package org.example.services;

import com.google.common.hash.Hashing;
import lombok.Getter;
import lombok.Setter;
import org.example.components.HibernateUtil;
import org.example.controllers.BookRepositoryImpl;
import org.example.controllers.MemberRepositoryImpl;
import org.example.controllers.WorkerRepositoryImpl;
import org.example.models.*;
import org.hibernate.Session;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service
@Getter
public class Service {
    List<Book> books;
    List<Member> members;
    List<Library_worker> library_workers;
    List<Loan> loans;
    List<Admin> admins;
    BookRepositoryImpl bookRepository;
    MemberRepositoryImpl memberRepository;
    WorkerRepositoryImpl workerRepository;

    @Getter
    @Setter
    int role = 0; // ? 0 - Гость, 1 - Читатель, 2 - Работник

    Session session;

    public Service() {
        session = HibernateUtil.getSessionFactory().openSession();

        // ! Repository
        bookRepository = new BookRepositoryImpl();
        memberRepository = new MemberRepositoryImpl();
        workerRepository = new WorkerRepositoryImpl();

        try {
            // ! Repository pattern
            books = bookRepository.getAllBooks();
            members = memberRepository.getAllMembers();
            library_workers = workerRepository.getAllWorkers();

            // TODO: сделать Loans и LoansRepository + LoansRepositoryImpl
            loans = session.createQuery("FROM Loan", Loan.class).list();

            admins = session.createQuery("FROM Admin", Admin.class).list();
        } catch (Exception e) {
            System.out.println("Ошибка при обработке данных таблицы.");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void addBook(Book book) {
        books.add(book);
        // ! Repository pattern
        bookRepository.addBook(book);
    }

    public void addWorker(Library_worker worker) {
        library_workers.add(worker);
        // ! Repository pattern
        workerRepository.addWorker(worker);
    }

    public boolean isUserValid(Person person) {
        // ? SHA-256 encryption
        person.setSha256_password(hashing_password(person.getSha256_password()));

        for (Member member : members) {
            if (member.getLogin().equals(person.getLogin()) &&
                    member.getSha256_password().equals(person.getSha256_password())) {
                role = 1; // ? Читатель
                System.out.println("ЧИТАТЕЛЬ");
                return true;
            }
        }

        for (Library_worker library_worker : library_workers) {
            if (library_worker.getLogin().equals(person.getLogin()) &&
                    library_worker.getSha256_password().equals(person.getSha256_password())) {
                role = 2; // ? Работник
                System.out.println("РАБОТНИК");
                return true;
            }
        }

        for (Admin admin : admins) {
            if (admin.getLogin().equals(person.getLogin()) &&
                    admin.getSha256_password().equals(person.getSha256_password())) {
                role = 3; // ? АДМИН
                System.out.println("АДМИН");
                return true;
            }
        }

        System.out.println("ФИАСКО ЛОГИН");
        return false;
    }

    public void registration_new_member(Member member) {
        member.setSha256_password(hashing_password(member.getSha256_password()));
        member.setRole(1);
        member.setId(members.get(members.size() - 1).getId() + 1);
        // ? Текущее время до миллисекунд
        // TODO: проверить
        member.setRegistration_date(new Date());

        members.add(member);
        // ! Repository pattern
        memberRepository.addMember(member);
    }

    public String hashing_password(String unhashing_password) {
        return Hashing.sha256()
                .hashString(unhashing_password, StandardCharsets.UTF_8)
                .toString();
    }

    public Member get_last_member() {
        return members.get(members.size() - 1);
    }

    public Library_worker get_last_worker() { return library_workers.get(library_workers.size() - 1); }
}
