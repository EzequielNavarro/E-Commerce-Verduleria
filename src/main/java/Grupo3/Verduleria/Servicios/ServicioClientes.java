package Grupo3.Verduleria.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Grupo3.Verduleria.Repositorios.RepositorioClientes;

@Service
public class ServicioClientes {

    @Autowired
    private RepositorioClientes repositorioClientes;
}
