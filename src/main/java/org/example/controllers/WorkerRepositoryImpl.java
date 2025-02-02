package org.example.controllers;

import org.example.components.HibernateUtil;
import org.example.interfaces.WorkerRepository;
import org.example.models.Library_worker;
import org.example.models.Member;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class WorkerRepositoryImpl implements WorkerRepository {
    private Session session;

    public WorkerRepositoryImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public List<Library_worker> getAllWorkers() {
        return session.createQuery("FROM Library_worker", Library_worker.class).list();
    }

    @Override
    public void addWorker(Library_worker worker) {
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            System.out.println("ID: " + worker.getId());
            session.save(worker);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
    }
}
