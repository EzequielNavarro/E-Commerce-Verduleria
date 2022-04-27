package Grupo3.Verduleria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @GetMapping("/carrito")
    public String carro() {
        return "carrito.html";
    }
}
