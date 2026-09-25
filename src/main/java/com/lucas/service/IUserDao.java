package com.lucas.service;

import com.lucas.exception.ModelException;
import com.lucas.model.User;

import java.util.List;

public interface IUserDao {

  void create(User user) throws ModelException;
  void update(User user) throws ModelException;
  void delete(String id) throws ModelException;
  List<User> findAll();
}
