package Grupo3.Verduleria.Servicios;

import Grupo3.Verduleria.Entidades.Clientes;
import Grupo3.Verduleria.Entidades.Compra;
import Grupo3.Verduleria.Entidades.ProductoKilo;
import Grupo3.Verduleria.Repositorios.RepositorioCompra;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCompra {

    @Autowired
    private RepositorioCompra repositorioCompra;
    
    public void Validator(List <ProductoKilo> listaProducto, Integer total) throws Exception{
        if (listaProducto == null || listaProducto.isEmpty())  {
            throw new Exception("La lista no es valida");
        }
        if (total == null || total < 1 ) {
            throw new Exception("El total no se puede procesar");
        }
    }
    
    public Compra carritoCompra(List <ProductoKilo> listaProducto, Clientes cliente, Integer total ){
        try {
            Validator(listaProducto, total);
        } catch (Exception ex) {
        }
        
        Compra compra = new Compra();
        compra.setProducto(listaProducto);
        compra.setCliente(cliente);
        compra.setTotal(total);
        repositorioCompra.save(compra);
        
        return compra;
    }
    
   
    
}
