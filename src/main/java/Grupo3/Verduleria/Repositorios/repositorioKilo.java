package Grupo3.Verduleria.Repositorios;

import Grupo3.Verduleria.Entidades.productoKilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositorioKilo  extends JpaRepository<productoKilo, String>{

}
