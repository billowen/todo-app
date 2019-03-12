package com.example.springvue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.MissingResourceException;

@RestController
@RequestMapping("/todos")
public class ApiController {
    private TodoRepository todoRepository;

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @PostMapping
    public Todo addTodo(@Valid @RequestBody Todo todo) {
//        Todo todo = new Todo();
//        todo.setTitle(title);
        return todoRepository.save(todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo != null) {
            todoRepository.delete(todo);
        }
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todo) {
        Todo existed = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Can not find todo"));

        existed.setTitle(todo.getTitle());
        existed.setCompleted(todo.getCompleted());

        return todoRepository.save(existed);
    }
}
