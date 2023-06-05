package com.student.work.repo;

import com.student.work.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Task getTaskById(Long id);
}
