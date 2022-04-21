package Grupo3.Verduleria.Repositorios;

import Grupo3.Verduleria.Entidades.ProductoKilo;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 349c3419ccc26f57ed1fc12b08a6ffec70b08bfe
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioKilo  extends JpaRepository<ProductoKilo, String>{
<<<<<<< HEAD
    
    @Query("SELECT k FROM ProductoKilo k WHERE nombre = :nombre")
    public List<ProductoKilo> buscarPorNombre(@Param("nombre")String nombre);
=======

>>>>>>> 349c3419ccc26f57ed1fc12b08a6ffec70b08bfe
}
