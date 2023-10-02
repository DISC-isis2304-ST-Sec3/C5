package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.TipoH;
import uniandes.edu.co.proyecto.repositorio.TipoHRepository;

@Controller
public class TipoHController {

    @Autowired
    private TipoHRepository tipoHRepository;

    @GetMapping("/tiposH")
    public String tipoH(Model model){
        model.addAttribute("tiposH", tipoHRepository.darTiposH());
        return "tiposH";
    }
    @GetMapping("/tiposH/new")
    public String tipoHForm(Model model){
        model.addAttribute("tipoH", new TipoH());
        return "tipoHNuevo";
    }
    @PostMapping("/tiposH/new/save")
    public String tipoHGuardar(@ModelAttribute TipoH tipoH){
        tipoHRepository.insertarTiposH(tipoH.getDotacion(), tipoH.getCapacidad(),tipoH.getCostoNoche());
        return "redirect:/tiposH";
    }
    @GetMapping("/tiposH/{nombreTipo}/edit")
    public String tipoHEditerForm(@PathVariable("nombreTipo") String nombreTipo, Model model){
        TipoH tipoH = tipoHRepository.darTipoH((nombreTipo));
        if(tipoH != null){
            model.addAttribute("tipoH", tipoH);
            return "tipoHEditar";
        } else {
            return "redirect:/tiposH";
        }
    }
    @PostMapping("/tiposH/{nombreTipo}/edit/save")
    public String tipoHEditarGuardar(@PathVariable("nombreTipo") String nombreTipo, @ModelAttribute TipoH tipoH){
        tipoHRepository.actualizarTiposH(nombreTipo, tipoH.getDotacion(), tipoH.getCapacidad(), tipoH.getCostoNoche());
        return "redirect:/tiposH";
    }
    @GetMapping("/tiposH/{nombreTipo}/edit/delete")
    public String tipoHEliminar(@PathVariable("nombreTipo") String nombreTipo){
        tipoHRepository.eliminarTipoH(nombreTipo);
        return "redirect:/tiposH";
    }
    
}
