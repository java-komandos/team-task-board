package com.student.work.service;
import com.student.work.entity.User;
import com.student.work.exception.TaskNotFoundException;
import com.student.work.exception.UserNotFoundException;

import java.net.UnknownServiceException;

public interface UserService {
    User createUser(String name, String email);
    User getUserById(Long id) throws UnknownServiceException;
    void deleteUserById(Long id);
    User addTaskToUser(Long user_id, Long task_id) throws TaskNotFoundException, UserNotFoundException;

    User deleteTaskFromUser(Long user_id, Long task_id) throws TaskNotFoundException, UserNotFoundException;
}
