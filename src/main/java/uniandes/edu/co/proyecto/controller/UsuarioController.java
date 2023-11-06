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
        usuarioRepository.insertarUsuario(usuario.getTipoDocumento(), usuario.getNombreUsuario(), usuario.getCorreo(), usuario.getTipoUsuario().getTipoUser());;
        return "redirect:/usuarios";
    }
    @GetMapping("/usuarios/{idUser}/edit")
    public String usuarioEditerForm(@PathVariable("idUser") int idUser, Model model){
        Usuario usuario = usuarioRepository.darUsuario(idUser);
        if(usuario != null){
            model.addAttribute("usuario", usuario);
            return "usuarioEditar";
        } else {
            return "redirect:/usuarios";
        }
    }
    @PostMapping("/usuarios/{idUser}/edit/save")
    public String servicioEditarGuardar(@PathVariable("idUser") int idUser, @ModelAttribute Usuario usuario){
        usuarioRepository.actualizarUsuario(idUser, usuario.getTipoDocumento(), usuario.getNombreUsuario(), usuario.getCorreo(), usuario.getTipoUsuario().getTipoUser());
        return "redirect:/usuarios";
    }
    
    @GetMapping("/usuarios/{idUser}/delere")
    public String usuarioEliminar(@PathVariable ("idUser") int idUser){
        usuarioRepository.eliminarUsuario(idUser);
        return "redirect:/usuarios";
    }
}
