package com.lucas.model;

import com.lucas.enums.Affiliation;
import com.lucas.exception.ModelException;

import java.util.Objects;

public class Professor extends User {

  final public static int PROFESSOR_ID_SIZE = 7;
  final public static int DEPARTMENT_SIZE = 32;

  private String professorId;
  private String department;

  public Professor() {
  }

  public Professor(String cpf, String name, String email, String password, Affiliation affiliation, String professorId, String department) throws ModelException {
    super(cpf, name, email, password, affiliation);
    this.setProfessorId(professorId);
    this.setDepartment(department);
  }

  public String getProfessorId() {
    return professorId;
  }

  public void setProfessorId(String professorId) throws ModelException {
    Professor.validateProfessorId(professorId);
    this.professorId = professorId;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) throws ModelException {
    Professor.validateDepartment(department);
    this.department = department;
  }

  public static void validateProfessorId(String professorId) throws ModelException {
    if (professorId == null || professorId.isEmpty())
      throw new ModelException("Validação do ID do Professor: o campo não pode nulo ou vazio");
    if (professorId.length() < PROFESSOR_ID_SIZE)
      throw new ModelException("Validação do ID do Professor: O nome excede o limite máximo de " + PROFESSOR_ID_SIZE + " caracteres.");
    for (int i = 0; i < PROFESSOR_ID_SIZE; i++)
      if (!Character.isDigit(professorId.charAt(i)))
        throw new ModelException("Validação do ID do Professor: o campo deve conter apenas dígitos");
  }

  public static void validateDepartment(String department) throws ModelException {
    if (department == null || department.trim().isEmpty()) {
      throw new ModelException("Validação do Departamento: O campo não pode ser nulo ou vazio.");
    }
    if (department.length() > DEPARTMENT_SIZE) {
      throw new ModelException("Validação do Departamento: O nome excede o limite máximo de " + DEPARTMENT_SIZE + " caracteres.");
    }
    for (int i = 0; i < department.length(); i++) {
      char c = department.charAt(i);
      if (!Character.isAlphabetic(c)) {
        throw new ModelException("Validação do Departamento: Caractere inválido '" + c + "' detectado na posição " + i + ". Use apenas letras.");
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Professor professor = (Professor) o;
    return Objects.equals(professorId, professor.professorId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(professorId);
  }


}
