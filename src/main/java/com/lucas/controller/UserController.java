package com.lucas.controller;

import com.lucas.dao.UserDao;
import com.lucas.enums.Affiliation;
import com.lucas.exception.ModelException;
import com.lucas.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserController {

  @FXML
  private TextField txtCpf;
  @FXML
  private TextField txtName;
  @FXML
  private TextField txtEmail;
  @FXML
  private PasswordField txtPassword;
  @FXML
  private Label lblStatus;
  @FXML
  private ComboBox<String> cbmAffiliation;

  private final UserDao userDao = new UserDao();

  public void handleRegister() {
    try {
      String cpf = txtCpf.getText();
      String name = txtName.getText();
      String email = txtEmail.getText();
      String password = txtPassword.getText();

      String selectedItem = cbmAffiliation.getSelectionModel().getSelectedItem();
      if (selectedItem == null) {
        lblStatus.setStyle("-fx-text-fill: red");
        lblStatus.setText("Por favor, selecione uma filiação.");
        return;
      }

      Affiliation affiliation = Affiliation.valueOf(selectedItem.toUpperCase());

      User newUser = new User(cpf, name, email, password, affiliation);

      userDao.create(newUser);

      lblStatus.setStyle("-fx-text-fill: green");
      lblStatus.setText("Usuário registrado e salvo com sucesso!");
      clearFields();
    } catch (ModelException e) {
      lblStatus.setStyle("-fx-text-fill: red");
      lblStatus.setText(e.getMessage());
    } catch (Exception e) {
      lblStatus.setStyle("-fx-text-fill: red");
      lblStatus.setText("Erro ao registrar: " + e.getMessage());
    }
  }

  private void clearFields() {
    txtCpf.clear();
    txtName.clear();
    txtEmail.clear();
    txtPassword.clear();
    cbmAffiliation.getSelectionModel().clearSelection();
  }
}
