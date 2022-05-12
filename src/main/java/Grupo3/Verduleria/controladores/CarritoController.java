package Grupo3.Verduleria.controladores;

import Grupo3.Verduleria.Entidades.Clientes;
import Grupo3.Verduleria.Entidades.DetalleOrden;
import Grupo3.Verduleria.Entidades.Orden;
import Grupo3.Verduleria.Entidades.ProductoKilo;
import Grupo3.Verduleria.Servicios.ServicioClientes;
import Grupo3.Verduleria.Servicios.ServicioCompra;
import Grupo3.Verduleria.Servicios.ServicioKilo;
import static java.lang.Math.log;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class CarritoController {

    @Autowired
    private ServicioCompra servicioCompra;
    @Autowired
    private ServicioKilo servicioKilo;
    @Autowired
    private ServicioClientes servicioClientes;

// para almacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    // datos de la orden
    Orden orden = new Orden();

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        //sesion
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "compra.html";
    }

    @PostMapping("/cart")  // boton listado compra
    public String addCart(@RequestParam String id, @RequestParam Integer cantidad, Model model) throws Exception {
        DetalleOrden detalleOrden = new DetalleOrden();
        ProductoKilo productoKilo = new ProductoKilo();
        double sumaTotal = 0;

        Optional<ProductoKilo> optionalProducto = servicioKilo.findById(id);
//        log.info("Producto añadido: {}", optionalProducto.get());
//        log.info("Cantidad: {}", cantidad);
        productoKilo = optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(productoKilo.getPrecio());
        detalleOrden.setNombre(productoKilo.getNombre());
        detalleOrden.setTotal(productoKilo.getPrecio() * cantidad);
        detalleOrden.setProductoKilo(productoKilo);

        //validar que le producto no se añada 2 veces
        String idProducto = productoKilo.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProductoKilo().getId().equals(idProducto));

        if (!ingresado) {
            detalles.add(detalleOrden);
        }

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);

        List<ProductoKilo> listaKilo = servicioKilo.findAll();
        model.addAttribute("lista", listaKilo); // muestra la lista

        return "Listado_Productos.html";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) throws Exception {

        Clientes clienteLogueado = (Clientes) session.getAttribute("usuariosession");
//        Clientes cl = servicioClientes.findById(clienteLogueado.getId());   asi queda solo si esta logueado

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", clienteLogueado);

        return "usuario/resumenorden";
    }

    // quitar un producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable String id, Model model) {

        // lista nueva de prodcutos
        List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

        for (DetalleOrden detalleOrden : detalles) {
            if (!detalleOrden.getProductoKilo().getId().equals(id)) {
                ordenesNueva.add(detalleOrden);
            }
        }

        // poner la nueva lista con los productos restantes
        detalles = ordenesNueva;

        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "compra.html";
    }

    @GetMapping("/comprafinal")
    public String compraExito(ModelMap model, HttpSession session) {
        Clientes clienteLogueado = (Clientes) session.getAttribute("usuariosession");
        
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", clienteLogueado);
        return "compraexito.html";
    }
}
