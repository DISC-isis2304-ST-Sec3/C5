package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.modelo.Consumo;
import uniandes.edu.co.proyecto.repositorio.ConsumoRepository;
import uniandes.edu.co.proyecto.repositorio.ConsumoRepository.RespuestaRFC5;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository.RespuestaRFC2;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository.RespuestaRFC4;



@Controller
public class ConsumoController {
    
    @Autowired
    private ConsumoRepository consumoRepository;

    @GetMapping("/consumos")
    public String consumo(Model model, String nombreUsuario, String fechaInicio, String fechaFin){

        if((nombreUsuario == null || nombreUsuario.equals("")) || (fechaInicio == null || fechaInicio.equals("")) || (fechaFin == null || fechaFin.equals("")))
        {
           model.addAttribute("consumos", consumoRepository.darConsumos());
        }
        else
        {
            Collection<RespuestaRFC5> RF5 = consumoRepository.darConsumoPorUsuarioYFechas(nombreUsuario, fechaInicio, fechaFin);
            model.addAttribute("idUser", RF5.iterator().next().getIDUser());
            model.addAttribute("nombreUsuario", RF5.iterator().next().getNombreUsuario());
            model.addAttribute("Total_Gastado_En_Consumos", RF5.iterator().next().getTotal_Gastado_En_Consumos());
        }
        
        return "consumos";
    }
    @GetMapping("/consumos/new")
    public String consumoForm(Model model){
        model.addAttribute("consumo", new Consumo());
        return "consumoNuevo";
    }
    @PostMapping("/consumos/new/save")
    public String consumoGuardar(@ModelAttribute Consumo consumo){
        consumoRepository.insertarConsumos(consumo.getDescripcion(), consumo.getFecha(), consumo.getCosto(), consumo.getServicios_idServicio().getIdServicio());
        return "redirect:/consumos";
    }
    @GetMapping("/consumos/{idConsumo}/edit")
    public String consumoEditerForm(@PathVariable("idConsumo") int idConsumo, Model model){
        Consumo consumo = consumoRepository.darConsumo(idConsumo);
        if(consumo != null){
            model.addAttribute("consumo", consumo);
            return "consumoEditar";
        } else {
            return "redirect:/consumos";
        }
    }
    @PostMapping("/consumos/{idConsumo}/edit/save")
    public String consumoEditarGuardar(@PathVariable("idConsumo") int idConsumo, @ModelAttribute Consumo consumo){
        consumoRepository.actualizarConsumos(idConsumo, consumo.getDescripcion(), consumo.getFecha(), consumo.getCosto(), consumo.getServicios_idServicio().getIdServicio());
        return "redirect:/consumos";
    }
    @GetMapping("/consumos/{idConsumo}/edit/delete")
    public String consumoEliminar(@PathVariable("idConsumo") int idConsumo){
        consumoRepository.eliminarConsumo(idConsumo);
        return "redirect:/consumos";
    }
}
