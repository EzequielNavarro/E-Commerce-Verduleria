package Grupo3.Verduleria.controladores;

import Grupo3.Verduleria.Entidades.Clientes;
import Grupo3.Verduleria.Servicios.ServicioClientes;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_ADMIN')")
@Controller
@RequestMapping("/")
public class ClienteController {

    @Autowired
    private ServicioClientes servicioClientes;

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_ADMIN')")
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_ADMIN')")
    @GetMapping("/editar-perfil")
    public String editarPerfil(HttpSession session, @RequestParam String id, ModelMap model) {

        //Evita que otro usuario logueado edite el perfil de otro comparando los id's y lo envia al inicio
        Clientes clienteLogueado = (Clientes) session.getAttribute("usuariosession");
        if (clienteLogueado == null || !clienteLogueado.getId().equals(id)) {
            return "redirect:/inicio";
        }

        try {
            Clientes cliente = servicioClientes.findById(id);
            model.addAttribute("perfil", cliente);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());

        }
        return "perfil.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE','ROLE_ADMIN')")
    @PostMapping("/actualizar-perfil")
    public String actualizarPerfil(ModelMap model, HttpSession session, @RequestParam String id, @RequestParam String nombre, @RequestParam Long dni, @RequestParam String correo, @RequestParam String clave1, @RequestParam String clave2) {
        Clientes cliente = null;

        try {
            Clientes clienteLogueado = (Clientes) session.getAttribute("usuariosession");
            if (clienteLogueado == null || !clienteLogueado.getId().equals(id)) {
                return "redirect:/inicio";
            }

            cliente = servicioClientes.findById(id);
            servicioClientes.edit(id, clave1, clave2, nombre, dni, correo);
            session.setAttribute("usuariosession", cliente); //esto actualiza los cambios del perfil del cliente actual a toda la pagina/session
            return "redirect:/inicio";
        } catch (Exception ex) {
            model.put("error", ex.getMessage());
            model.put("perfil", cliente);
            return "perfil.html";
        }

    }
    @GetMapping("/contacto-cliente")
    public String contactoCliente(){
        return "contacto_cliente.html";
    }
}
