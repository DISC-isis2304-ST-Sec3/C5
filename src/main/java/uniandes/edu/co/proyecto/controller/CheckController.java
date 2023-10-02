package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Check;
import uniandes.edu.co.proyecto.repositorio.CheckRepository;



@Controller
public class CheckController {

    @Autowired
    private CheckRepository checkRepository;

    @GetMapping("/checks")
    public String check(Model model){
        model.addAttribute("checks", checkRepository.darChecks());
        return "checks";
    }
    @GetMapping("/checks/new")
    public String checkForm(Model model){
        model.addAttribute("checks", new Check());
        return "checkNuevo";
    }
    @PostMapping("/checks/new/save")
    public String checkGuardar(@ModelAttribute Check check){
        checkRepository.insertarChecks(check.getFechaIn(), check.getFechaOut());
        return "redirect:/checks";
    }
    @GetMapping("/checks/{Reservas_idReserva}/edit")
    public String checkEditerForm(@PathVariable("Reservas_idReserva") int Reservas_idReserva, Model model){
        Check check = checkRepository.darCheck(Reservas_idReserva);
        if(check != null){
            model.addAttribute("check", check);
            return "checkEditar";
        } else {
            return "redirect:/checks";
        }
    }
    @PostMapping("/checks/{Reservas_idReserva}/edit/save")
    public String checkEditarGuardar(@PathVariable("Reservas_idReserva") int Reservas_idReserva, @ModelAttribute Check check){
        checkRepository.actualizarChecks(Reservas_idReserva, check.getFechaIn(), check.getFechaOut());
        return "redirect:/checks";
    }
    @GetMapping("/checks/{Reservas_idReserva}/edit/delete")
    public String checkEliminar(@PathVariable("Reservas_idReserva") int Reservas_idReserva){
        checkRepository.eliminarCheck(Reservas_idReserva);;
        return "redirect:/checks";
    }
    
}
