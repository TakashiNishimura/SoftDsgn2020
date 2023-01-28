package com.example.wsp_spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DB関連のRepositoryです.
 */
@Repository
public class DBRepository {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public DBRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public AuthnData findAuthnData(String userId, String userPassword) {
    String sql = """
        SELECT 
          user_id, user_password, user_name
        FROM
          authn_data
        WHERE
          user_id = ? AND user_password = ?
        """;

    try {
      return jdbcTemplate.queryForObject(sql,
          new BeanPropertyRowMapper<>(AuthnData.class),
          userId,
          userPassword);
    } catch (DataAccessException e) {
      return null;
    }
  }

  public int insertAuthnData(AuthnData authnData) {
    String sql = """
        INSERT INTO
          AUTHN_DATA
        VALUES
          (?, ?, ?)
        """;

    try {
      return jdbcTemplate.update(sql,
          authnData.getUserId(), authnData.getUserPassword(), authnData.getUserName());
    } catch (DataAccessException e) {
      return -1;
    }
  }

  public List<ToDoItem> findToDoItems(String userId) {
    String sql = """
        SELECT
          SUBJECT, BODY
        FROM 
          TODO_ITEM
        WHERE
          USER_ID = ?
        """;

    try {
      return jdbcTemplate.query(sql,
          new BeanPropertyRowMapper<>(ToDoItem.class),
          userId);
    } catch (DataAccessException e) {
      return null;
    }
  }

  public int insertToDoItem(String userId, String subject, String body) {
    String sql = """
        INSERT INTO
          TODO_ITEM
        VALUES
          (?, ?, ?)
        """;

    try {
      return jdbcTemplate.update(sql,
          userId,
          subject,
          body);
    } catch (DataAccessException e) {
      return -1;
    }
  }

}
