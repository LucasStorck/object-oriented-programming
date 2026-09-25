package com.lucas.dao;

import java.io.*;

public abstract class Dao<T> {

  private final String fileName;

  public Dao(String fileName) {
    this.fileName = fileName;
  }

  @SuppressWarnings("unchecked")
  protected T[] loadData() {
    File file = new File(fileName);
    if (!file.exists()) {
      return null;
    }

    try (FileInputStream fileInputStream = new FileInputStream(file);
         ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

      return (T[]) objectInputStream.readObject();

    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Erro ao carregar os dados do arquivo " + fileName + ": " + e.getMessage());
      return null;
    }
  }

  protected void saveData(T[] data) {
    try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
         ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

      objectOutputStream.writeObject(data);

    } catch (IOException e) {
      System.err.println("Erro crítico ao salvar os dados no arquivo " + fileName + ": " + e.getMessage());
    }
  }
}
