package Grupo3.Verduleria.controladores;

import Grupo3.Verduleria.Servicios.ServicioClientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ServicioClientes servicioClientes;

    @GetMapping("/")
    public String index(@RequestParam (required = false) String login, ModelMap model) {
        if (login != null) {
            model.put("exito", "Logueado con exito");
        }
        return "index.html";
    }

//    @GetMapping("/login")
//    public String login(@RequestParam (required = false) String error, @RequestParam (required = false) String logout, ModelMap model) {
//    if (error != null) {
//    model.put("error", "Usuario o Contraseña incorrectos");
//}
//    if (logout != null) {
//    model.put("logout","Desconectado correctamente");
//}
//        return "login.html";
//    }
    
    @GetMapping("/registro")
    public String registro() {
        return "registro.html";
    }
    
    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam String nombre, @RequestParam String clave, @RequestParam String clave2, @RequestParam Long dni, @RequestParam String correo) {

        try {
            servicioClientes.save(nombre, clave, clave2, dni, correo);
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("clave", clave);
//          modelo.put("clave2", clave2);
            modelo.put("dni", dni);
            modelo.put("correo", correo);
            return null;
        }
        modelo.put("exito","Se ha registrado en nuestro sistema correctamente");
        return "inicio.html";
    }
}
