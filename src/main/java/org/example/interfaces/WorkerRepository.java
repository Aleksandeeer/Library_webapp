package org.example.interfaces;

import org.example.models.Book;
import org.example.models.Library_worker;

import java.util.List;

public interface WorkerRepository {
    List<Library_worker> getAllWorkers();
    void addWorker(Library_worker worker);
}
