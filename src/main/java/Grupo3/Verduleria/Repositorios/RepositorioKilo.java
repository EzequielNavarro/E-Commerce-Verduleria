package Grupo3.Verduleria.Repositorios;

import Grupo3.Verduleria.Entidades.ProductoKilo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioKilo  extends JpaRepository<ProductoKilo, String>{
    
    @Query("SELECT k FROM ProductoKilo k WHERE nombre = :nombre")
    public List<ProductoKilo> buscarPorNombre(@Param("nombre")String nombre);
}
