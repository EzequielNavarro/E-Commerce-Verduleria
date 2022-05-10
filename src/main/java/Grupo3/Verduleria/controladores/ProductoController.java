package Grupo3.Verduleria.controladores;

import Grupo3.Verduleria.Entidades.Clientes;
import Grupo3.Verduleria.Entidades.ProductoKilo;
import Grupo3.Verduleria.Servicios.ServicioKilo;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/productoslista")
public class ProductoController {

    @Autowired
    private ServicioKilo servicioKilo;

    List<ProductoKilo> lista_producto = new ArrayList<ProductoKilo>();

    @GetMapping
    public String listaProductos(ModelMap model, HttpSession session) throws Exception {
        List<ProductoKilo> listaKilo = servicioKilo.findAll();
        model.addAttribute("lista", listaKilo); // muestra la lista

        return "Listado_Productos.html";
    }

    @PostMapping("/addproducto")
    public String agregar(ModelMap model, @RequestParam String nombre, @RequestParam Integer precio, @RequestParam Integer kilo) throws Exception {
        try {
            servicioKilo.save(nombre, precio, kilo);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "Admin.html";
        }
        List<ProductoKilo> listaKilo = servicioKilo.findAll();
        model.addAttribute("lista", listaKilo); // muestra la lista
        return "Listado_Productos.html";
    }

    @GetMapping("/delproducto")
    public String borrar(ModelMap model, @RequestParam String id) throws Exception {
        try {
            servicioKilo.delete(id);
        } catch (Exception ex) {
            model.addAttribute("error", "Hubo un problema: " + ex.getMessage());
        }
        List<ProductoKilo> listaKilo = servicioKilo.findAll();
        model.addAttribute("lista", listaKilo); // muestra la lista
        return "Listado_Productos.html";
    }

    @GetMapping("/editproducto")
    public String editar(ModelMap model, @RequestParam String id) throws Exception {
        ProductoKilo PK = servicioKilo.BuscarById(id);
        model.put("producto", PK);
        model.put("titulo", "Editar Producto");
        return "Admin.html";
    }

    @PostMapping("/modproducto")
    public String modificar(ModelMap model, @RequestParam(required = false) String id, @RequestParam String nombre, @RequestParam Integer precio, @RequestParam Integer kilo) {
        try {
            servicioKilo.edit(id, nombre, precio, kilo);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "Admin.html";
    }
}
