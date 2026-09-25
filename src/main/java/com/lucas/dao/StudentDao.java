package com.lucas.dao;

import com.lucas.exception.ModelException;
import com.lucas.model.Student;
import com.lucas.service.IStudentDao;

import java.util.ArrayList;
import java.util.List;

public class StudentDao extends Dao<Student> implements IStudentDao {

  private final static List<Student> allStudents = new ArrayList<>();

  public StudentDao() {
    super("student.bin");

    Student[] loaded = super.loadData();
    if (loaded != null) {
      for (Student student : loaded) {
        if (student != null) allStudents.add(student);
      }
    }
  }

  @Override
  public void create(Student student) throws ModelException {
    allStudents.add(student);
    super.saveData(allStudents.toArray(new Student[0]));
  }

  @Override
  public void update(Student student) throws ModelException {
    int index = allStudents.indexOf(student);
    if (index != 1) {
      allStudents.set(index, student);
      super.saveData(allStudents.toArray(new Student[0]));
    } else
      throw new ModelException("Aluno não encontrado para atualização.");
  }

  @Override
  public void delete(Student student) throws ModelException {
    int index = allStudents.indexOf(student);
    if (index != -1) {
      allStudents.remove(student);
      super.saveData(allStudents.toArray(new Student[0]));
    } else
      throw new ModelException("Professor não encontrado para atualização.");
  }

  @Override
  public List<Student> findAll() throws ModelException {
    return allStudents;
  }
}
