package com.lucas.dao;

import com.lucas.exception.ModelException;
import com.lucas.model.User;
import com.lucas.service.IUserDao;

import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao<User> implements IUserDao {

  private static final List<User> allUsers = new ArrayList<>();

  public UserDao() {
    super("users.bin");

    User[] loaded = super.loadData();
    if (loaded != null) {
      allUsers.clear();
      for (User user : loaded) {
        if (user != null) allUsers.add(user);
      }
    }
  }

  @Override
  public void create(User user) throws ModelException {
    allUsers.add(user);
    super.saveData(allUsers.toArray(new User[0]));
  }

  @Override
  public void update(User user) throws ModelException {
    int index = allUsers.indexOf(user);
    if (index != -1) {
      allUsers.set(index, user);
      super.saveData(allUsers.toArray(new User[0]));
    } else {
      throw new ModelException("Usuário não encontrado para atualização.");
    }
  }

  @Override
  public void delete(String id) throws ModelException {
    User userFound = null;
    for (User u : allUsers) {
      if (u.getId().equals(id)) {
        userFound = u;
        break;
      }
    }
    if (userFound != null) {
      allUsers.remove(userFound);
      super.saveData(allUsers.toArray(new User[0]));
    } else {
      throw new ModelException("Usuário não encontrado para exclusão.");
    }
  }

  @Override
  public List<User> findAll() {

    return allUsers;
  }
}
