package Grupo3.Verduleria.Entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Compra {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @OneToMany
    private List <ProductoKilo> producto;

    @ManyToOne
    private Clientes cliente;

    private Integer total;

    public Compra(List<ProductoKilo> producto, Clientes cliente, Integer total) {
        this.producto = producto;
        this.cliente = cliente;
        this.total = total;
    }


    public Compra() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProductoKilo> getProducto() {
        return producto;
    }

    public void setProducto(List<ProductoKilo> producto) {
        this.producto = producto;
    }


    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes Cliente) {
        this.cliente = Cliente;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer Total) {
        this.total = Total;
    }

    @Override
    public String toString() {
        return "Compra{" + "id=" + id + ", producto=" + producto + ", cliente=" + cliente + ", total=" + total + '}';
    }

}
