package com.lucas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/viewer/register-view.fxml"));

    primaryStage.setTitle("User Management Context");
    primaryStage.setScene(new Scene(root, 400, 600));
    primaryStage.show();
  }

  public static void main(String[] args) {
    Application.launch(MainApp.class, args);
  }
}
