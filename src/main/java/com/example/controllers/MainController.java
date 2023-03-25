package com.example.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.entities.Correo;
import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.services.CorreoService;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;
import com.example.services.TelefonoService;

@Controller
@RequestMapping("/")

public class MainController {

    private static final Logger LOG = Logger.getLogger("MainController");
    
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private TelefonoService telefonoService;

    @Autowired
    private CorreoService correoService;

    @GetMapping("/listar")
    public ModelAndView listar(){

        List<Empleado> empleados = empleadoService.findAll();

        ModelAndView mav = new ModelAndView("views/listarEmpleados");
        
        mav.addObject("empleados", empleados);
        
        return mav;
    }
    @GetMapping("frmAltaEmpleado")
    public String formularioAltaEmpleado (Model model) {
        
        List<Departamento> departamentos = departamentoService.findAll();

        Empleado empleado = new Empleado();

        model.addAttribute("empleado", empleado);
        model.addAttribute("departamentos", departamentos);
        
        return "views/formularioAltaEmpleado";

    
    }

    @PostMapping("/altaModificacionEmpleado")
    public String altaEstudiante(@ModelAttribute Empleado empleado,
                                @RequestParam(name="numerosTelefonos") String telefonosRecibidos,
                                @RequestParam(name = "emailsCorreos") String correosRecibidos) {

        LOG.info("Telefonos recibidos: " + telefonosRecibidos);
        LOG.info("Correos recibidos: " + correosRecibidos);

        empleadoService.save(empleado);

        List<String> listadoNumerosTelefonos = null;

        if(telefonosRecibidos != null) {

          String[] arrayTelefonos = telefonosRecibidos.split(";");

          listadoNumerosTelefonos = Arrays.asList(arrayTelefonos);

        }

        if(listadoNumerosTelefonos != null) {
            telefonoService.deleteByEmpleado(empleado);;
            listadoNumerosTelefonos.stream().forEach(n -> {
              Telefono  telefonoObject = Telefono
                .builder()
                .numero(n)
                .empleado(empleado)
                .build();
              telefonoService.save(telefonoObject);
  
            });
          }

          List<String> listadoEmailsCorreos = null;
          
          if(correosRecibidos != null) {

            String[] arrayCorreos = correosRecibidos.split(";");
  
            listadoEmailsCorreos = Arrays.asList(arrayCorreos);
  
          }
  
          if(listadoEmailsCorreos != null) {
              correoService.deleteByEmpleado(empleado);;
              listadoEmailsCorreos.stream().forEach(c -> {
                Correo  correoObject = Correo
                  .builder()
                  .email(c)
                  .empleado(empleado)
                  .build();
                correoService.save(correoObject);
    
              });
            }
            
                     



        return "redirect:/listar";
    }



}
