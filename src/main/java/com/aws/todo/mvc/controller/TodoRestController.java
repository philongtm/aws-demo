package com.aws.todo.mvc.controller;

import com.aws.todo.mvc.service.TodoCreatorService;
import com.aws.todo.mvc.service.TodoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoRestController {
    @Autowired
    private TodoStorageService storageService;

    @Autowired
    private TodoCreatorService creatorService;

    private static final String SUCCESS = "{\"success\":true}";

    @PostMapping(path = "/addTodo")
    public String addTodo(@RequestParam("todo-title") String title) {
        storageService.addTodo(creatorService.createTodo(title));
        return SUCCESS;
    }

    @PostMapping(path = "/list")
    public String getAllTodo(@RequestParam("status") String status) {
        String todos = storageService.getTodos(status);
        return todos;
    }

    @DeleteMapping(path = "/todos/completed")
    public String deleteAllCompleted() {
        storageService.deleteCompleted();
        return SUCCESS;
    }

    @PutMapping(path = "todos/toggle_all")
    @RequestMapping(value = "todos/toggle_all", method = RequestMethod.PUT)
    public String toggleAll(@RequestParam("toggle-all") String complete) {
        storageService.toggleAll(complete.equals("true"));
        return SUCCESS;
    }

    @DeleteMapping(value = "/todos/{id}")
    public String deleteById(@PathVariable("id") String id) {
        storageService.removeBy(Long.parseLong(id));
        return SUCCESS;
    }

    @PutMapping(value = "/todos/{id}")
    public String updateById(@PathVariable("id") String id, @RequestParam("todo-title") String title) {
        storageService.updateTodo(Long.parseLong(id), title);
        return SUCCESS;
    }

    @GetMapping(value = "todos/{id}")
    public String getTodo(@PathVariable("id") String id) {
        storageService.getTodo(Long.parseLong(id));
        return SUCCESS;
    }

    @PutMapping(value = "/todos/{id}/toggle_status")
    public String toggleStatusById(@PathVariable("id") String id, @RequestParam("status") String status) {
        storageService.toggleStatus(Long.parseLong(id), status.equals("true"));
        return SUCCESS;
    }
}
