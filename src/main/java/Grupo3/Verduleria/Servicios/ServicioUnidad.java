package Grupo3.Verduleria.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Grupo3.Verduleria.Repositorios.RepositorioUnidad;

@Service
public class ServicioUnidad {

    @Autowired
    private RepositorioUnidad repositorioUnidad;

}
