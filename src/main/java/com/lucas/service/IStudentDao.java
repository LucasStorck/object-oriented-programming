package com.lucas.service;

import com.lucas.exception.ModelException;
import com.lucas.model.Student;

import java.util.List;

public interface IStudentDao {

  void create(Student student) throws ModelException;
  void update(Student student) throws ModelException;
  void delete(Student student) throws ModelException;
  List<Student> findAll() throws ModelException;
}
