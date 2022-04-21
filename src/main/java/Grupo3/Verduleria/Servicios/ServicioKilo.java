package Grupo3.Verduleria.Servicios;

import Grupo3.Verduleria.Entidades.ProductoKilo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Grupo3.Verduleria.Repositorios.RepositorioKilo;

@Service
public class ServicioKilo {

    @Autowired
    private RepositorioKilo repositorioKilo;

    public void Validator(String nombre, Integer precio, Integer kilo) throws Exception{
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no es valido");
        }
        if (precio == null || precio < 1) {
            throw new Exception("El precio no es valido");
        }
        if (kilo == null || kilo <1 ) {
            throw new Exception("El kilaje no es valido");
        }
    }
            
    //// CURD
    
    public ProductoKilo save(String nombre, Integer precio, Integer kilo){
        try {
            Validator(nombre,precio,kilo);
        } catch (Exception e) {
            System.out.println(e);
        }
      ProductoKilo PK = new ProductoKilo();
   
   
        return repositorioKilo.save(PK);
    }
    
}
