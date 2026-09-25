module com.lucas {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires java.base;

  opens com.lucas.controller to javafx.fxml;
  exports com.lucas;
  exports com.lucas.enums;
}