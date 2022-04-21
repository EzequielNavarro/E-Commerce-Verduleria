package Grupo3.Verduleria.Servicios;

import Grupo3.Verduleria.Entidades.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Grupo3.Verduleria.Repositorios.RepositorioClientes;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioClientes {

    @Autowired
    private RepositorioClientes repositorioClientes;
   

    public void Validator(String nombre, Long dni,String correo) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no es valido");
        }
        if (dni == null || dni < 1) {
            throw new Exception("El dni no es valido");
        }
        if (correo == null || correo.trim().isEmpty()) {
            throw new Exception("Correo invalido");
        }
    }

    //// CURD
    @Transactional
    public Clientes save(String nombre, Long dni, String correo) throws Exception {
        Validator(nombre, dni, correo);

        Clientes CT = new Clientes();
        CT.setNombre(nombre);
        CT.setDni(dni);
        CT.setCorreo(correo);
        return repositorioClientes.save(CT);
    }

    @Transactional
    public Clientes edit(String id, String nombre, Long dni, String correo) throws Exception {
        Optional<Clientes> respuesta = repositorioClientes.findById(id);
        if (respuesta.isPresent()) {
            Clientes CT = respuesta.get();
            CT.setNombre(nombre);
            CT.setDni(dni);
            CT.setCorreo(correo);
            return repositorioClientes.save(CT);
        } else {
            return null;
        }
    }

    @Transactional
    public void delete(String id) throws Exception {
        repositorioClientes.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Clientes> findAll()  throws Exception {
        return repositorioClientes.findAll();
    }

     @Transactional(readOnly = true)
    public Clientes findById(String id)  throws Exception{
        return  repositorioClientes.getById(id);
    }
    
    @Transactional(readOnly = true)
    public Clientes buscarPorDNI (Long dni) throws Exception{
        return repositorioClientes.buscarPorDNI(dni);

    }

}
