package Grupo3.Verduleria.Repositorios;

import Grupo3.Verduleria.Entidades.productoKilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioKilo  extends JpaRepository<productoKilo, String>{

}
