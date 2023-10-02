package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository;



@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public String usuario(Model model){
        model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        return "usuarios";
    }
    @GetMapping("/usuarios/new")
    public String usuarioForm(Model model){
        model.addAttribute("usuario", new Usuario());
        return "usuarioNuevo";
    }
    @PostMapping("/usuarios/new/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario){
        usuarioRepository.insertarUsuarios(usuario.getTipoDocumento(), usuario.getNombreUsuario(), usuario.getCorreo(), usuario.getTipoUsuario().getNombre());
        return "redirect:/usuarios";
    }
    @GetMapping("/usuarios/{cedula}/edit")
    public String usuarioEditerForm(@PathVariable("cedula") int cedula, Model model){
        Usuario usuario = usuarioRepository.darUsuario(cedula);
        if(usuario != null){
            model.addAttribute("servicio", usuario);
            return "usuarioEditar";
        } else {
            return "redirect:/usuarios";
        }
    }
    @PostMapping("/usuarios/{cedula}/edit/save")
    public String servicioEditarGuardar(@PathVariable("cedula") int cedula, @ModelAttribute Usuario usuario){
        usuarioRepository.actualizarUsuarios(cedula, usuario.getTipoDocumento(), usuario.getNombreUsuario(), usuario.getCorreo(), usuario.getTipoUsuario().getNombre());;
        return "redirect:/usuarios";
    }
    @GetMapping("/usuarios/{cedula}/edit/delete")
    public String servicioEliminar(@PathVariable("cedula") int cedula){
        usuarioRepository.eliminarUsuario(cedula);;
        return "redirect:/usuarios";
    }
}
