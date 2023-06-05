package com.student.work.service;

import com.student.work.entity.Task;
import com.student.work.exception.TaskNotFoundException;

public interface TaskService {
    Task createTask(String title, boolean isCompleted);
    Task updateTask(Long id, String title, boolean isCompleted);

    Task getTaskById(Long id) throws TaskNotFoundException;

    void deleteTaskById(Long id);
}
