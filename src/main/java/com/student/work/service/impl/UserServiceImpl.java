package com.student.work.service.impl;

import com.student.work.entity.Task;
import com.student.work.entity.User;
import com.student.work.exception.TaskNotFoundException;
import com.student.work.exception.UserNotFoundException;
import com.student.work.repo.UserRepository;
import com.student.work.service.TaskService;
import com.student.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.UnknownServiceException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TaskService taskService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TaskService taskService) {
        this.userRepository = userRepository;
        this.taskService = taskService;
    }

    @Override
    public User createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws UnknownServiceException {
        User user = userRepository.getUserById(id);
        if (user!=null){
            return user;
        }else {
            throw new UnknownServiceException("User not found with id: " + id);
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User addTaskToUser(Long user_id, Long task_id) throws TaskNotFoundException, UserNotFoundException {
        User user = userRepository.getUserById(user_id);
        if (user == null) throw new UserNotFoundException("User not found with id: " + user_id);
        Task task = taskService.getTaskById(task_id);
        Set<Task> taskSet = user.getTasks();
        taskSet.add(task);
        user.setTasks(taskSet);

        return userRepository.save(user);
    }

    @Override
    public User deleteTaskFromUser(Long user_id, Long task_id) throws TaskNotFoundException, UserNotFoundException {
        User user = userRepository.getUserById(user_id);
        if (user == null) throw new UserNotFoundException("User not found with id: " + user_id);
        Task task = taskService.getTaskById(task_id);
        user.getTasks().remove(task);

        return userRepository.save(user);
    }
}
