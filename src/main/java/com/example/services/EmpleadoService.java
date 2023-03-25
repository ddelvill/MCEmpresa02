package com.example.services;

import java.util.List;

import com.example.entities.Empleado;

public interface EmpleadoService {
    public List<Empleado> findAll();
    public Empleado findById(int idEmpleado);
    public void save(Empleado empleado);
    public void deleteById(int idEmpleado);
    public void delete(Empleado empleado);

}
