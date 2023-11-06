package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.TipoUsuario;
import uniandes.edu.co.proyecto.repositorio.TipoUsuarioRepository;



@Controller
public class TipoUsuarioController {
    
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping("/tiposUsuarios")
    public String tiposUsuarios(Model model){
        model.addAttribute("tiposUsuarios", tipoUsuarioRepository.darTiposUsuarios());
        return "tiposUsuarios";
    }

    @GetMapping("/tiposUsuarios/new")
    public String tipoUsuarioForm(Model model){
        model.addAttribute("tipoUsuario", new TipoUsuario());
        return "tipoUsuarioNuevo";
    }

    @PostMapping("/tiposUsuarios/new/save")
    public String tipoUsuarioGuardar(@ModelAttribute TipoUsuario tipoUsuario){
        tipoUsuarioRepository.insertarTiposUsuarios();
        return "redirect:/tiposUsuarios";
    }

    @GetMapping("/tiposUsuarios/{tipoUser}/edit")
    public String tipoUsuarioEditarForm(@PathVariable("tipoUser") String tipoUser, Model model){
        TipoUsuario tipoUsuario = tipoUsuarioRepository.darTipoUsuario(tipoUser);
        if(tipoUsuario != null){
            model.addAttribute("tipoUsuario", tipoUsuario);
            return "tipoUsuarioEditar";
        } else{
            return "redirect:/tiposUsuarios";
        }
    }

    @PostMapping("/tiposUsuarios/{tipoUser}/edit/save")
    public String tipoUsuarioEditarGuardar(@PathVariable("tipoUser") String tipoUser, @ModelAttribute TipoUsuario tipoUsuario){
        tipoUsuarioRepository.actualizarTiposUsuarios(tipoUser);
        return "redirect:/tiposUsuarios";
    }

    @GetMapping("/tiposUsuarios/{tipoUser}/delete")
    public String tipoUsuarioEliminar(@PathVariable ("tipoUser") String tipoUser){
        tipoUsuarioRepository.eliminarTipoUsuario(tipoUser);
        return "redirect:/tiposUsuarios";
    }
  
}
