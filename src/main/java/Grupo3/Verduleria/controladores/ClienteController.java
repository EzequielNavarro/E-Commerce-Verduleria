package Grupo3.Verduleria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
public class ClienteController {
    
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    }
}
