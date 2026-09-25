package com.lucas.service;

import com.lucas.exception.ModelException;
import com.lucas.model.Professor;
import java.util.List;

public interface IProfessorDao {

  void create(Professor professor) throws ModelException;
  void update(Professor professor) throws ModelException;
  void delete(String id) throws ModelException;
  List<Professor> findAll();
}
