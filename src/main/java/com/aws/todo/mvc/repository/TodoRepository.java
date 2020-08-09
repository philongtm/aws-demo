package com.aws.todo.mvc.repository;

import com.aws.todo.mvc.entity.Todo;
import com.aws.todo.mvc.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * TodoRepository Class.
 */
public interface TodoRepository extends JpaRepository<Todo, Long> {

    /**
     * getAllByStatus
     *
     * @param status
     *
     * @return List<Todo>
     */
    List<Todo> getAllByStatus(@Param("status") Status status);

    /**
     * getAllByStatus
     *
     * @param status
     *
     * @return List<Todo>
     */
    List<Todo> getAllByStatus(@Param("status") String status);
}
