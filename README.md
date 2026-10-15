# object-oriented-programming

A Java practice project for object-oriented programming: a small user registration app with a JavaFX
form, built to exercise inheritance, encapsulation, validation and a layered design.

## Running

Requires Java 21 and Maven.

```bash
mvn javafx:run
```

This opens the **User Registration System** window (CPF, name, email, password and affiliation).
Registered users are saved to a `users.bin` file in the working directory.

## Code structure

```
com.lucas.model       -> User and its subclasses Student and Professor (with field validation)
com.lucas.enums       -> Affiliation (PROFESSOR, STUDENT)
com.lucas.exception   -> ModelException, thrown when validation fails
com.lucas.dao         -> Dao<T> base class (file persistence) and the User/Student/Professor DAOs
com.lucas.service     -> DAO interfaces (IUserDao, IStudentDao, IProfessorDao)
com.lucas.controller  -> JavaFX controllers
resources/viewer      -> register-view.fxml, the registration form
```

## Notes

- **Validation lives in the models**: every setter validates its input and throws `ModelException`
  on invalid data, so an invalid object can never be built.
- **Persistence** uses plain Java serialization, written by the generic `Dao<T>` base class.
- This is a learning project: passwords are stored as plain text in `users.bin`, so it is not meant
  for real use.
