package com.example.wsp_spring.controller;

import com.example.wsp_spring.model.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * ToDoリストです.
 */
@Controller
public class ToDoController {

  private final ToDoService toDoService;

  @Autowired
  public ToDoController(ToDoService toDoService) {
    this.toDoService = toDoService;
  }

  @GetMapping("/todo")
  public String getToDo(Model model) {
    model.addAllAttributes(toDoService.getToDoAttributeMap());
    return "view/todo_list";
  }

  @PostMapping("/todo")
  public String postToDo(String subject, String body, Model model) {
    model.addAttribute("userValue", toDoService.getSignedUserValue());
    model.addAttribute("todoItems", toDoService.additionalShowToDoItems(subject, body));
    return "view/todo_list";
  }

}
