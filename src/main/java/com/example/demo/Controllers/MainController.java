package com.example.demo.Controllers;

import com.example.demo.Models.ToDoItem;
import com.example.demo.Repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    ToDoRepository toDoRepo;

    @RequestMapping("/")
    public String listToDos(Model model)
    {
        model.addAttribute("todos", toDoRepo.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String toDoForm(Model model){
        model.addAttribute("todo", new ToDoItem());
        return "todoform";
    }

    @PostMapping("/process")
    public String processForm(@Valid ToDoItem todo, Model model, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute(todo);
            return "todoform";
        }

        toDoRepo.save(todo);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showToDo(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("todo", toDoRepo.findById(id).get());
        model.addAttribute("todos", toDoRepo.findAll());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateToDo(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("todo", toDoRepo.findById(id).get());
        return "todoform";
    }

    @RequestMapping("/delete/{id}")
    public String delToDo(@PathVariable("id") long id)
    {
        toDoRepo.deleteById(id);
        return "redirect:/";
    }
}
