package com.java.Todo.controller;

import com.java.Todo.model.Task;
import com.java.Todo.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PrimaryController {

    private TaskManagementService taskManagementService;
    private List<Task> todos;

    public PrimaryController() {
        todos = new ArrayList<Task>();
    }

    @Autowired
    public void setTaskManagementService(TaskManagementService taskManagementService) {
        this.taskManagementService = taskManagementService;
    }

    @GetMapping("/")
    public String index(Model model) {
        todos = taskManagementService.findAll();
        int completed = 0;
        int pending = 0;

        for(Task t : todos) {
            if(t.getCompleted())
                completed++;
        }
        pending = todos.size() - completed;

        model.addAttribute("completed", completed);
        model.addAttribute("pending", pending);
        model.addAttribute("todos", todos);
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam(name = "desc") String description) {
        Task todo = new Task.TaskBuilder(description).withCompleted(false ).build();
        taskManagementService.addTask(todo);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateTask(@PathVariable Long id) {
        Task todo = taskManagementService.findById(id);
        todo.setCompleted(!todo.getCompleted());
        taskManagementService.addTask(todo);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskManagementService.deleteById(id);
        return "redirect:/";
    }
}
