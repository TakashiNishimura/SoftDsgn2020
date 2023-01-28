package com.example.wsp_spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * ToDoのServiceです.
 */
@Service
public class ToDoService {

  private final DBRepository dbRepository;
  private final SignService signService;

  @Autowired
  public ToDoService(DBRepository dbRepository, SignService signService) {
    this.dbRepository = dbRepository;
    this.signService = signService;
  }

  public List<ToDoItem> showToDoItems() {
    return this.getToDoItemList(this.getSignedUserValue());
  }

  public List<ToDoItem> additionalShowToDoItems(String subject, String body) {
    var signedUser = this.getSignedUserValue();
    if (Objects.nonNull(signedUser)) {
      dbRepository.insertToDoItem(signedUser.getUserId(), subject, body);
    }
    return this.getToDoItemList(signedUser);
  }

  private List<ToDoItem> getToDoItemList(UserValue userValue) {
    return dbRepository.findToDoItems(userValue.getUserId());
  }

  public UserValue getSignedUserValue() {
    return signService.whoIsSigned();
  }

  public Map<String, Object> getToDoAttributeMap() {
    return Map.of(
        "userValue", this.getSignedUserValue(),
        "todoItems", this.showToDoItems()
    );
  }

}
