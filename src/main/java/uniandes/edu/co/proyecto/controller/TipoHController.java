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
        model.addAttribute("tipoH", tipoHRepository.darTiposH());
        return "tiposH";
    }
    @GetMapping("/tiposH/new")
    public String tipoHForm(Model model){
        model.addAttribute("tipoH", new TipoH());
        return "tipoHNuevo";
    }
    @PostMapping("/tiposH/new/save")
    public String tipoHGuardar(@ModelAttribute TipoH tipoH){
        tipoHRepository.insertarTiposH(tipoH.getDotacion(), tipoH.getCapacidad(),tipoH.getCostoPorNoche());
        return "redirect:/tiposH";
    }
    @GetMapping("/tiposH/{nombreTH}/edit")
    public String tipoHEditerForm(@PathVariable("nombreTH") String nombreTH, Model model){
        TipoH tipoH = tipoHRepository.darTipoH((nombreTH));
        if(tipoH != null){
            model.addAttribute("tipoH", tipoH);
            return "tipoHEditar";
        } else {
            return "redirect:/tiposH";
        }
    }
    @PostMapping("/tiposH/{nombreTH}/edit/save")
    public String tipoHEditarGuardar(@PathVariable("nombreTH") String nombreTH, @ModelAttribute TipoH tipoH){
        tipoHRepository.actualizarTiposH(nombreTH, tipoH.getDotacion(), tipoH.getCapacidad(), tipoH.getCostoPorNoche());
        return "redirect:/tiposH";
    }
    @GetMapping("/tiposH/{nombreTH}/edit/delete")
    public String tipoHEliminar(@PathVariable("nombreTH") String nombreTH){
        tipoHRepository.eliminarTipoH(nombreTH);
        return "redirect:/tiposH";
    }
    
}
