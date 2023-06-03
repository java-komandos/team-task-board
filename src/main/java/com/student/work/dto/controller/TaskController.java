package com.student.work.dto.controller;

import com.student.work.dto.TaskDto;
import com.student.work.entity.Task;
import com.student.work.exception.TaskNotFoundException;
import com.student.work.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody @Valid TaskDto taskDto){
        Task task = taskService.createTask(taskDto.getTitle(), taskDto.isCompleted());
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping
    public Task updateTask(@RequestBody TaskDto taskDto){
        return taskService.updateTask(taskDto.getId(), taskDto.getTitle(), taskDto.isCompleted());

    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) throws TaskNotFoundException {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping(value = "{id}")
    public void deleteTaskById(@PathVariable("id") Long id){
        taskService.deleteTaskById(id);
    }

}
