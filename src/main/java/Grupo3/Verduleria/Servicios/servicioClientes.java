package Grupo3.Verduleria.Servicios;

import Grupo3.Verduleria.Repositorios.repositorioClientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class servicioClientes {

    @Autowired
    private repositorioClientes repositorioClientes;
}
