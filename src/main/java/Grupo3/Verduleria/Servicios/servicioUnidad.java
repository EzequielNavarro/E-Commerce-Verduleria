package Grupo3.Verduleria.Servicios;

import Grupo3.Verduleria.Repositorios.repositorioUnidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class servicioUnidad {

    @Autowired
    private repositorioUnidad repositorioUnidad;

}
