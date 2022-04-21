package Grupo3.Verduleria.Servicios;

import Grupo3.Verduleria.Entidades.ProductoKilo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Grupo3.Verduleria.Repositorios.RepositorioKilo;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioKilo {

    @Autowired
    private RepositorioKilo repositorioKilo;

    public void Validator(String nombre, Integer precio, Integer kilo) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no es valido");
        }
        if (precio == null || precio < 1) {
            throw new Exception("El precio no es valido");
        }
        if (kilo == null || kilo < 1) {
            throw new Exception("El kilaje no es valido");
        }
    }

    //// CURD
    @Transactional
    public ProductoKilo save(String nombre, Integer precio, Integer kilo) throws Exception {
        Validator(nombre, precio, kilo);

        ProductoKilo PK = new ProductoKilo();

        PK.setNombre(nombre);
        PK.setPrecio(precio);
        PK.setKilo(kilo);
        return repositorioKilo.save(PK);
    }

    @Transactional
    public ProductoKilo edit(String id, String nombre, Integer precio, Integer kilo) throws Exception {
        Optional<ProductoKilo> respuesta = repositorioKilo.findById(id);
        if (respuesta.isPresent()) {
            ProductoKilo PK = respuesta.get();
            PK.setNombre(nombre);
            PK.setPrecio(precio);
            PK.setKilo(kilo);
            return repositorioKilo.save(PK);
        } else {
            return null;
        }
    }

    @Transactional
    public void delete(String id) throws Exception {
        repositorioKilo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProductoKilo> findAll()  throws Exception {
        return repositorioKilo.findAll();
    }

     @Transactional(readOnly = true)
    public ProductoKilo findById(String id)  throws Exception{
        return  repositorioKilo.getById(id);
    }
    
    @Transactional(readOnly = true)
    public List<ProductoKilo> buscarPorNombre(String nombre) throws Exception{
        return repositorioKilo.buscarPorNombre(nombre);
    }

}
