package com.example.demo.Repositories;

import com.example.demo.Models.ToDoItem;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDoItem, Long> {
}
