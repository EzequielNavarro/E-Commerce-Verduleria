package Grupo3.Verduleria.Servicios;

import Grupo3.Verduleria.Entidades.ProductoUnidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Grupo3.Verduleria.Repositorios.RepositorioUnidad;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioUnidad {

    @Autowired
    private RepositorioUnidad repositorioUnidad;


    public void Validator(String nombre, Integer precio, Integer unidad) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no es valido");
        }
        if (precio == null || precio < 1) {
            throw new Exception("El precio no es valido");
        }
        if (unidad == null || unidad < 1) {
            throw new Exception("Las unidades no corresponden");
        }
    }

    //// CURD
    @Transactional
    public ProductoUnidad save(String nombre, Integer precio, Integer unidad) throws Exception {
        Validator(nombre, precio, unidad);

        ProductoUnidad PU = new ProductoUnidad();
        PU.setNombre(nombre);
        PU.setPrecio(precio);
        PU.setUnidad(unidad);
        return repositorioUnidad.save(PU);
    }

    @Transactional
    public ProductoUnidad edit(String id, String nombre, Integer precio, Integer Unidad) throws Exception {
        Optional<ProductoUnidad> respuesta = repositorioUnidad.findById(id);
        if (respuesta.isPresent()) {
            ProductoUnidad PU = respuesta.get();
            PU.setNombre(nombre);
            PU.setPrecio(precio);
            PU.setUnidad(Unidad);
            return repositorioUnidad.save(PU);
        } else {
            return null;
        }
    }

    @Transactional
    public void delete(String id) throws Exception {
        repositorioUnidad.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProductoUnidad> findAll()  throws Exception {
        return repositorioUnidad.findAll();
    }

     @Transactional(readOnly = true)
    public ProductoUnidad findById(String id)  throws Exception{
        return  repositorioUnidad.getById(id);
    }
    
    @Transactional(readOnly = true)
    public List<ProductoUnidad> buscarPorNombre(String nombre) throws Exception{
        return repositorioUnidad.buscarPorNombre(nombre);
    }

}
