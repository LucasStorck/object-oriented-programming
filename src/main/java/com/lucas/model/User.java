package com.lucas.model;

import com.lucas.enums.Affiliation;
import com.lucas.exception.ModelException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class User implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  public static final int CPF_SIZE = 14;
  public static final int NAME_SIZE = 16;
  public static final int EMAIL_SIZE = 254;
  public static final int MIN_PASSWORD_SIZE = 8;

  private final String id;
  private String cpf;
  private String name;
  private String email;
  private String password;
  private Affiliation affiliation;

  public User() {
    this.id = UUID.randomUUID().toString();
  }

  public User(String cpf, String name, String email, String password, Affiliation affiliation) throws ModelException {
    this.id = UUID.randomUUID().toString();
    this.setCpf(cpf);
    this.setName(name);
    this.setEmail(email);
    this.setPassword(password);
    this.setAffiliation(affiliation);
  }

  public String getId() {
    return id;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) throws ModelException {
    User.validateCpf(cpf);
    this.cpf = cpf;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) throws ModelException {
    User.validateName(name);
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) throws ModelException {
    User.validateEmail(email);
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) throws ModelException {
    User.validatePassword(password);
    this.password = password;
  }

  public Affiliation getAffiliation() {
    return affiliation;
  }

  public void setAffiliation(Affiliation affiliation) throws ModelException {
    this.affiliation = affiliation;
  }

  public static void validateCpf(String cpf) throws ModelException {
    if (cpf == null || cpf.isEmpty()) {
      throw new ModelException("Validação do CPF: O campo não pode ser nulo ou vazio.");
    }
    if (cpf.length() != CPF_SIZE) {
      throw new ModelException("Validação do CPF: Tamanho incorreto. O formato obrigatório é 999.999.999-99.");
    }

    for (int i = 0; i < CPF_SIZE; i++) {
      char c = cpf.charAt(i);
      switch (i) {
        case 3:
        case 7:
          if (c != '.') {
            throw new ModelException("Validação do CPF: Caractere inválido na posição " + i + ". Deve ser um ponto ('.').");
          }
          break;
        case 11:
          if (c != '-') {
            throw new ModelException("Validação do CPF: Caractere inválido na posição " + i + ". Deve ser um hífen ('-').");
          }
          break;
        default:
          if (!Character.isDigit(c)) {
            throw new ModelException("Validação do CPF: Caractere inválido na posição " + i + ". Devem ser utilizados apenas números.");
          }
          break;
      }
    }
  }

  public static void validateName(String name) throws ModelException {
    if (name == null || name.trim().isEmpty()) {
      throw new ModelException("Validação do Nome: O campo não pode ser nulo ou vazio.");
    }
    if (name.length() > NAME_SIZE) {
      throw new ModelException("Validação do Nome: O nome excede o limite máximo de " + NAME_SIZE + " caracteres.");
    }

    for (int i = 0; i < name.length(); i++) {
      char c = name.charAt(i);
      if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c)) {
        throw new ModelException("Validação do Nome: Caractere inválido '" + c + "' detectado na posição " + i + ". Use apenas letras e espaços.");
      }
    }
  }

  public static void validateEmail(String email) throws ModelException {
    if (email == null || email.isEmpty()) {
      throw new ModelException("Validação do E-mail: O campo não pode ser nulo ou vazio.");
    }
    if (email.length() > EMAIL_SIZE) {
      throw new ModelException("Validação do E-mail: O endereço excede o limite máximo de " + EMAIL_SIZE + " caracteres.");
    }

    int atIndex = -1;
    int lastDotIndex = -1;
    int prevChar = ' ';

    for (int i = 0; i < email.length(); i++) {
      char c = email.charAt(i);

      if (c == '@') {
        if (atIndex != -1) {
          throw new ModelException("Validação do E-mail: Formato inválido. O e-mail só pode conter um símbolo '@'.");
        }
        if (i == 0) {
          throw new ModelException("Validação do E-mail: O símbolo '@' não pode estar no início do endereço.");
        }
        atIndex = i;
      } else if (c == '.') {
        if (prevChar == '.') {
          throw new ModelException("Validação do E-mail: Formato inválido. Não é permitido o uso de pontos consecutivos ('..').");
        }
        if (i == 0 || i == email.length() - 1) {
          throw new ModelException("Validação do E-mail: O endereço de e-mail não pode começar nem terminar com ponto ('.').");
        }
        if (atIndex != -1) {
          if (prevChar == '@') {
            throw new ModelException("Validação do E-mail: Formato inválido. O ponto ('.') não pode ficar imediatamente após o '@'.");
          }
          lastDotIndex = i;
        }
      } else {
        if (atIndex == -1) {
          if (!Character.isLetterOrDigit(c) && c != '_' && c != '%' && c != '+' && c != '-') {
            throw new ModelException("Validação do E-mail: Caractere inválido detectado na parte local (antes do @) na posição " + i + ".");
          }
        } else {
          if (!Character.isLetterOrDigit(c) && c != '-') {
            throw new ModelException("Validação do E-mail: Caractere inválido detectado no domínio (depois do @) na posição " + i + ".");
          }
          if (c == '-' && (prevChar == '.' || prevChar == '@')) {
            throw new ModelException("Validação do E-mail: Formato inválido. Um hífen não pode ficar ao lado de um ponto ou do símbolo '@'.");
          }
        }
      }
      prevChar = c;
    }

    if (atIndex == -1 || lastDotIndex == -1 || lastDotIndex < atIndex) {
      throw new ModelException("Validação do E-mail: Estrutura inválida. O e-mail precisa conter o '@' e o ponto delimitador do domínio.");
    }

    int tldLength = email.length() - 1 - lastDotIndex;
    if (tldLength < 2 || tldLength > 6) {
      throw new ModelException("Validação do E-mail: O sufixo de extensão do domínio (ex: .com, .br) deve ter entre 2 e 6 caracteres.");
    }
  }

  public static void validatePassword(String password) throws ModelException {
    if (password == null || password.isEmpty()) {
      throw new ModelException("Validação da Senha: O campo não pode ser nulo ou vazio.");
    }
    if (password.length() < MIN_PASSWORD_SIZE) {
      throw new ModelException("Validação da Senha: Senha muito curta. Ela deve possuir no mínimo " + MIN_PASSWORD_SIZE + " caracteres.");
    }

    boolean hasUpperCase = false;
    boolean hasLowerCase = false;
    boolean hasDigit = false;

    for (int i = 0; i < password.length(); i++) {
      char c = password.charAt(i);

      if (Character.isUpperCase(c)) {
        hasUpperCase = true;
      } else if (Character.isLowerCase(c)) {
        hasLowerCase = true;
      } else if (Character.isDigit(c)) {
        hasDigit = true;
      }
    }

    if (!hasUpperCase) {
      throw new ModelException("Validação da Senha: A senha deve conter pelo menos uma letra maiúscula.");
    }
    if (!hasLowerCase) {
      throw new ModelException("Validação da Senha: A senha deve conter pelo menos uma letra minúscula.");
    }
    if (!hasDigit) {
      throw new ModelException("Validação da Senha: A senha deve conter pelo menos um número.");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "User{" +
            "cpf='" + cpf + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
