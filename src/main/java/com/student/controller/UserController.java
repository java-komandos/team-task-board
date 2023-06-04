package com.student.work.controller;

import com.student.work.dto.UserDto;
import com.student.work.entity.Task;
import com.student.work.entity.User;
import com.student.work.exception.TaskNotFoundException;
import com.student.work.exception.UserNotFoundException;
import com.student.work.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownServiceException;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDto userDto){
        User user = userService.createUser(userDto.getName(), userDto.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws UnknownServiceException {
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
    }

    @PutMapping(value = "/{user_id}/task/{task_id}")
    public ResponseEntity<User> userByIdtakeTaskById(@PathVariable("user_id") Long user_id,@PathVariable("task_id") Long task_id) throws UserNotFoundException, TaskNotFoundException {
        User user = userService.addTaskToUser(user_id, task_id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/{user_id}/task/{task_id}")
    public ResponseEntity<User> deleteTaskFromUserById(@PathVariable("user_id") Long user_id,@PathVariable("task_id") Long task_id) throws UserNotFoundException, TaskNotFoundException {
        User user = userService.deleteTaskFromUser(user_id, task_id);
        return ResponseEntity.ok().body(user);
    }

}
