package com.aws.todo.mvc.service;

import com.aws.todo.mvc.entity.Status;
import com.aws.todo.mvc.entity.Todo;
import com.aws.todo.mvc.repository.TodoRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoStorageService {

    @Autowired
    private TodoCreatorService todoCreator;

    @Autowired
    private TodoRepository todoRepository;

    private Todo getTodoById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Todo not found"));
    }

    public void addTodo(Todo todo) {
        todoCreator.createTodo(todo.getTitle());
        todoRepository.save(todo);
    }

    public String getTodos(String status) {
        List<Todo> todoList;
        if (status == null || status.isEmpty()) {
            todoList = todoRepository.findAll();
        } else {
            todoList = todoRepository.findAll()
                    .stream()
                    .filter(todo -> todo.getStatus().toString().equals(status.toUpperCase()))
                    .collect(Collectors.toList());
        }
        JSONArray array = new JSONArray();
        for (Todo todo : todoList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", todo.getId());
            jsonObject.put("title", todo.getTitle());
            jsonObject.put("completed", todo.isComplete());
            array.put(jsonObject);
        }
        return array.toString(2);
    }

    public Todo getTodo(Long id) {
        return getTodoById(id);
    }

    public void updateTodo(Long updatedId, String updatedTitle) {
        Todo todoToUpdate = getTodoById(updatedId);
        todoToUpdate.setTitle(updatedTitle);
        todoRepository.save(todoToUpdate);
    }

    public void deleteCompleted() {
        for (Todo todo : todoRepository.findAll()) {
            if (todo.isComplete()) {
                todoRepository.delete(todo);
            }
        }
    }

    public void toggleStatus(Long id, boolean isComplete) {
        Todo todoToToggle = getTodoById(id);
        if (todoToToggle.getId() == id) {
            todoToToggle.setStatus(isComplete ? Status.COMPLETE : Status.ACTIVE);
        }
        todoRepository.save(todoToToggle);
    }

    public void toggleAll(boolean isComplete) {
        for (Todo todo : todoRepository.findAll()) {
            todo.setStatus(isComplete ? Status.COMPLETE : Status.ACTIVE);
            todoRepository.save(todo);
        }
    }

    public void removeBy(long id) {
        Todo todoToRemove = getTodoById(id);
        todoRepository.delete(todoToRemove);
    }
}
