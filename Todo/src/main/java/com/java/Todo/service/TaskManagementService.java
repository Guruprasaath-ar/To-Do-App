package com.java.Todo.service;

import com.java.Todo.model.Task;
import com.java.Todo.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskManagementService {

    private TaskRepo taskRepo;

    @Autowired
    public void setTaskRepo(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public void addTask(Task task) {
        taskRepo.save(task);
    }

    public List<Task> findAll() {
        return taskRepo.findAll();
    }

    public Task findById(Long id) {
        return taskRepo.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        taskRepo.deleteById(id);
    }

}
