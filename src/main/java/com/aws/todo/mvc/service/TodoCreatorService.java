package com.aws.todo.mvc.service;

import com.aws.todo.mvc.entity.Todo;
import com.aws.todo.mvc.entity.Status;
import org.springframework.stereotype.Service;

@Service
public class TodoCreatorService {

    public Todo createTodo(String title) {
        Todo todo = Todo.builder()
                .title(title)
                .status(Status.ACTIVE)
                .build();
        return todo;
    }

}
