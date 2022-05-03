package Grupo3.Verduleria.controladores;

import Grupo3.Verduleria.Entidades.Clientes;
import Grupo3.Verduleria.Entidades.ProductoKilo;
import Grupo3.Verduleria.Servicios.ServicioCompra;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class CarritoController {

    @Autowired
    private ServicioCompra servicioCompra;

    @GetMapping("/carrito")
    public String carro() {
        return "carrito.html";
    }

    @PostMapping("comprar")
    public String comprar(ModelMap model, @RequestParam Clientes cliente, @RequestParam List<ProductoKilo> listaProducto, @RequestParam Integer total) {

        try {
            model.put("lista", listaProducto);
            model.put("total", total);
            servicioCompra.carritoCompra(listaProducto, cliente, total);
        } catch (Exception ex) {

            model.put("error", ex.getMessage());
            return "Carrito.html";
        }
        model.put("exito", "La compra se realizo con exito");
        return "Listado_Productos";

    }

}
