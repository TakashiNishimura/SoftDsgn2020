package com.example.wsp_spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ToDoリストの内容のDataです.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoItem {
  private String subject;
  private String body;
}
