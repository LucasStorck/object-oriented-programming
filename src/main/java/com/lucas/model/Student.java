package com.lucas.model;

import com.lucas.enums.Affiliation;
import com.lucas.exception.ModelException;

public class Student extends User {

  final public static int STUDENT_ID_SIZE = 9;
  final public static int ENROLLMENT_ZIE = 32;
  final public static int YEAR_SIZE = 2;

  private String studentId;
  private String enrollment;
  private String year;

  public Student() {}

  public Student(String cpf, String name, String email, String password, Affiliation affiliation, String studentId, String enrollment, String year) throws ModelException {
    super(cpf, name, email, password, affiliation);
    this.setStudentId(studentId);
    this.setEnrollment(enrollment);
    this.setYear(year);
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) throws ModelException {
    Student.validateStudentId(studentId);
    this.studentId = studentId;
  }

  public String getEnrollment() {
    return enrollment;
  }

  public void setEnrollment(String enrollment) throws ModelException {
    Student.validateEnrollment(enrollment);
    this.enrollment = enrollment;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public static void validateStudentId(String studentId) throws ModelException {
    if (studentId == null || studentId.isEmpty())
      throw new ModelException("Validação do ID do Estudante: o campo não pode nulo ou vazio");
    if (studentId.length() < STUDENT_ID_SIZE)
      throw new ModelException("Validação do ID do Estudante: O nome excede o limite máximo de " + STUDENT_ID_SIZE + " caracteres.");
    for (int i = 0; i < STUDENT_ID_SIZE; i++)
      if (!Character.isDigit(studentId.charAt(i)))
        throw new ModelException("Validação do ID do Estudante: o campo deve conter apenas dígitos");
  }

  public static void validateEnrollment(String enrollment) throws ModelException {
    if (enrollment == null || enrollment.trim().isEmpty()) {
      throw new ModelException("Validação do Curso: O campo não pode ser nulo ou vazio.");
    }
    if (enrollment.length() > ENROLLMENT_ZIE) {
      throw new ModelException("Validação do Curso: O nome excede o limite máximo de " + ENROLLMENT_ZIE + " caracteres.");
    }
    for (int i = 0; i < enrollment.length(); i++) {
      char c = enrollment.charAt(i);
      if (!Character.isAlphabetic(c)) {
        throw new ModelException("Validação do Curso: Caractere inválido '" + c + "' detectado na posição " + i + ". Use apenas letras.");
      }
    }
  }
}
