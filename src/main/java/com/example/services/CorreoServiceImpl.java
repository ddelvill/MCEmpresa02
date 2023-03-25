package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.CorreoDao;
import com.example.entities.Correo;
import com.example.entities.Empleado;

@Service
public class CorreoServiceImpl implements CorreoService{
    @Autowired
    private CorreoDao correoDao;

    @Override
    public List<Correo> findAll() {
        return correoDao.findAll();
        }

    @Override
    public Correo findById(int idCorreo) {
        return correoDao.findById(idCorreo).get();
    }

    @Override
    @Transactional
    public void save(Correo correo) {
        correoDao.save(correo);
    }

    @Override
    @Transactional
    public void deleteById(int idCorreo) {
        correoDao.deleteById(idCorreo);;
    }

    @Override
    @Transactional
    public void deleteByEmpleado(Empleado empleado) {
        correoDao.deleteByEmpleado(empleado);
    }

    @Override
    public List<Correo> findbyEmpleado(Empleado empleado) {
        return correoDao.findByEmpleado(empleado);
    }
    
}
