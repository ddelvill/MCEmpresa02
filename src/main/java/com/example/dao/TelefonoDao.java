package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Empleado;
import com.example.entities.Telefono;

public interface TelefonoDao extends JpaRepository<Telefono,Integer> {
   
 long deleteByEmpleado(Empleado empleado);

 List<Telefono> findByEmpleado(Empleado empleado);
    
}
