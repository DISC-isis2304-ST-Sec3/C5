package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Empleado;
import uniandes.edu.co.proyecto.repositorio.EmpleadoRepository;

@Controller
public class EmpleadosController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping("/empleados")
    public String empleados(Model model){
        model.addAttribute("empleados", empleadoRepository.darEmpleados());
        return "empleados";
    }

    @GetMapping("/empleados/new")
    public String empleadoForm(Model model){
        model.addAttribute("empleado", new Empleado());
        return "empleadoNuevo";
    }

    @PostMapping("/empleados/new/save")
    public String empleadoGurardar(@ModelAttribute Empleado empleado){
        empleadoRepository.insertarEmpleado(empleado.getTiposUsuarios_tipoUser().getTipoUser());
        return "redirect:/empleados";
    }

    @GetMapping("/empleados/{idEmpleado}/edit")
    public String empleadoEditarForm(@PathVariable("idEmpleado") int idEmpleado, Model model){
        Empleado empleado = empleadoRepository.darEmpleado(idEmpleado);
        if(empleado != null){
            model.addAttribute("empleado", empleado);
            return "empleadoEditar";
        } else{
            return "redirect:/empleados";
        }
    }

    @PostMapping("/empleados/{idEmpleado}/edit/save")
    public String empleadoEditarGuardar(@PathVariable("idEmpleado") int idEmpleado, @ModelAttribute Empleado empleado){
        empleadoRepository.actualizarEmpleado(idEmpleado, empleado.getTiposUsuarios_tipoUser().getTipoUser());
        return "redirect:/empleados";
    }

    @GetMapping("/empleados/{idEmpleado}/delete")
    public String empleadoEliminar(@PathVariable ("idEmpleado") int idEmpleado){
        empleadoRepository.eliminarEmpleado(idEmpleado);
        return "redirect:/empleados";
    }

    
}
