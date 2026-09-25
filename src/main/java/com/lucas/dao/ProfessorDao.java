package com.lucas.dao;

import com.lucas.exception.ModelException;
import com.lucas.model.Professor;
import com.lucas.service.IProfessorDao;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDao extends Dao<Professor> implements IProfessorDao {

  private static final List<Professor> allProfessors = new ArrayList<>();

  public ProfessorDao() {
    super("professors.bin");

    Professor[] loaded = super.loadData();
    if (loaded != null)
      for (Professor professor : loaded) {
        if (professor != null) allProfessors.add(professor);
      }
  }

  @Override
  public void create(Professor professor) throws ModelException {
    allProfessors.add(professor);
    super.saveData(allProfessors.toArray(new Professor[0]));
  }

  @Override
  public void update(Professor professor) throws ModelException {
    int index = allProfessors.indexOf(professor);
    if (index != 1) {
      allProfessors.set(index, professor);
      super.saveData(allProfessors.toArray(new Professor[0]));
    } else {
      throw new ModelException("Professor não encontrado para atualização.");
    }
  }

  @Override
  public void delete(String id) throws ModelException {
    Professor professorFound = null;
    for (Professor professor : allProfessors) {
      if (professor.getId().equals(id)) {
        professorFound = professor;
        break;
      }
    }
    if (professorFound != null) {
      allProfessors.remove(professorFound);
      super.saveData(allProfessors.toArray(new Professor[0]));
    } else
      throw new ModelException("Professor não encontrado para atualização.");
  }

  @Override
  public List<Professor> findAll() {
    return allProfessors;
  }
}
