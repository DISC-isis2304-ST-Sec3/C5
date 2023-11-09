package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Usuario;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository.RespuestaRFC2;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository.RespuestaRFC4;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository.RespuestaRFC8;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository.RespuestaRFC10;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository.RespuestaRFC12;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository.RespuestaRFC7;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository.RespuestaRFC9;
import uniandes.edu.co.proyecto.repositorio.UsuarioRepository;



@Controller
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public String usuario(Model model, String RF9NombreServicio, String RF9FechaInicio, String RF9FechaFin, String RF10NombreServicio, String RF10FechaInicio, String RF10FechaFin){

        Collection<RespuestaRFC7> RF7 = usuarioRepository.darBuenosClientes();
        //model.addAttribute("idUser", RF7.iterator().next().getIDUser());
        //model.addAttribute("nombreUsuario", RF7.iterator().next().getNombreUsuario());
        //model.addAttribute("Información de buen Cliente", RF7.iterator().next().getInfoBuenCliente());
        

        Collection<RespuestaRFC12> RF12 = usuarioRepository.darClientesExcelentes();
        //model.addAttribute("idUser", RF12.iterator().next().getIDUser());
        //model.addAttribute("tipoDocumento", RF12.iterator().next().getTipoDocumento());
        //model.addAttribute("correo", RF12.iterator().next().getCorreo());
        //model.addAttribute("nombreUsuario", RF12.iterator().next().getNombreUsuario());
        //model.addAttribute("Razon de ser un Excelente Cliente", RF12.iterator().next().getRazon());

        if((RF9NombreServicio == null || RF9NombreServicio.equals("")) || (RF9FechaInicio == null || RF9FechaInicio.equals("")) || (RF9FechaFin == null || RF9FechaFin.equals("")) || (RF10NombreServicio == null || RF10NombreServicio.equals("")) || (RF10FechaInicio == null || RF10FechaInicio.equals("")) || (RF10FechaFin == null || RF10FechaFin.equals("")))
        {
            model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        }
        else if ((RF9NombreServicio == null || RF9NombreServicio.equals("")) || (RF9FechaInicio == null || RF9FechaInicio.equals("")) || (RF9FechaFin == null || RF9FechaFin.equals("")))
        {
            Collection<RespuestaRFC10> RF10 = usuarioRepository.consultarConsumov2(RF10NombreServicio, RF10FechaInicio, RF10FechaFin);
            model.addAttribute("nombreServicio", RF10.iterator().next().getIDUser());
            model.addAttribute("fecha", RF10.iterator().next().getTipoDocumento());
            model.addAttribute("duracion", RF10.iterator().next().getCorreo());
            model.addAttribute("costo", RF10.iterator().next().getNombreUsuario());
        }else
        {
            Collection<RespuestaRFC9> RF9 = usuarioRepository.consultarConsumo(RF9NombreServicio, RF9FechaInicio, RF9FechaFin);
            model.addAttribute("nombreServicio", RF9.iterator().next().getIDUser());
            model.addAttribute("fecha", RF9.iterator().next().getTipoDocumento());
            model.addAttribute("duracion", RF9.iterator().next().getCorreo());
            model.addAttribute("costo", RF9.iterator().next().getNombreUsuario());
            model.addAttribute("costo", RF9.iterator().next().getNumeroDeConsumos());
        }
        
        
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
