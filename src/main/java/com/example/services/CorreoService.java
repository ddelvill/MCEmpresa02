package com.example.services;

import java.util.List;

import com.example.entities.Correo;
import com.example.entities.Empleado;

public interface CorreoService {
    public List<Correo> findAll();
    public Correo findById(int idCorreo);
    public void save(Correo correo);
    public void deleteById(int idCorreo);
    public void deleteByEmpleado(Empleado empleado);
    public List<Correo> findbyEmpleado(Empleado empleado);

}
