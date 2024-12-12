package org.makar.t1_tasks.repository;

import org.makar.t1_tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
