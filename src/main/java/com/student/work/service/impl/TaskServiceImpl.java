package com.student.work.service.impl;

import com.student.work.entity.Task;
import com.student.work.exception.TaskNotFoundException;
import com.student.work.repo.TaskRepository;
import com.student.work.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(String title, boolean isCompleted) {
        Task task = new Task();
        task.setCompleted(isCompleted);
        task.setTitle(title);
        task.setCreationDate(LocalDate.now());

        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, String title, boolean isCompleted) {
        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setCompleted(isCompleted);
        task.setCreationDate(LocalDate.now());
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) throws TaskNotFoundException{
        Task task = taskRepository.getTaskById(id);
        if (task != null){
            return task;
        }else{
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}
