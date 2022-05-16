package Grupo3.Verduleria.controladores;

import Grupo3.Verduleria.Servicios.ServicioClientes;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")

@RequestMapping("/")
public class AdminController {

    @Autowired
    private ServicioClientes ServicioClientes;

    @GetMapping("/admin")
    public String admin(){
        return "Admin.html";
    }

    @GetMapping("/role/{id}")
    public String cambiarRol(ModelMap model, @PathVariable String id) {
        try {
            ServicioClientes.cambiarRol(id);
            model.put("", id);
        } catch (Exception e) {

        }
        return "redirect:/admin/";
    }
    @GetMapping ("/eliminar/{id}")
    public String eliminar (@PathVariable String id){
        try {
            ServicioClientes.delete(id);
        } catch (Exception e){
            
        }
        return "redirect:/admin/";
    }
}
