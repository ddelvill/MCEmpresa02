package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.DepartamentoDao;
import com.example.entities.Departamento;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{
    @Autowired
    private DepartamentoDao departamentoDao;
    @Override
    public List<Departamento> findAll() {
        return departamentoDao.findAll();
    }

    @Override
    public Departamento findById(int idDepartamento) {
        
        return departamentoDao.findById(idDepartamento).get();
    }

    @Override
    @Transactional
    public void save(Departamento departamento) {
        departamentoDao.save(departamento);
    }

    @Override
    public void deleteById(int idDepartamento) {
        departamentoDao.deleteById(idDepartamento);;
    }
    
}
