package com.digital.Clase23ClinicaOdontologica.repository;

import java.util.List;

public interface IDao<T>{
    //alta, buscarID, eliminar,modificar

    T guardar(T t);
    T buscarID(int id);
    void eliminar (int id);
    void modificar(T t);
    List<T> listar();
    T buscarXString(String valor);
}