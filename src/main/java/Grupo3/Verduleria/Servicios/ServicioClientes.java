package Grupo3.Verduleria.Servicios;

import Grupo3.Verduleria.Entidades.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Grupo3.Verduleria.Repositorios.RepositorioClientes;
import Grupo3.Verduleria.enums.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class ServicioClientes implements UserDetailsService {

    @Autowired
    private RepositorioClientes repositorioClientes;

    public void Validator(String nombre, String clave, String clave2, Long dni, String correo) throws Exception {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no es valido");
        }
        if (clave == null || clave.isEmpty() || clave.length() < 6) {
            throw new Exception("La clave no puede ser nula y tiene que tener mas de 6 carácteres");
        }

        if (!clave.equals(clave2)) {
            throw new Exception("Las claves no pueden ser distintas");
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

    public Clientes save(String nombre, String clave, String clave2, Long dni, String correo) throws Exception {
        Validator(nombre, clave, clave2, dni, correo);

        Clientes CT = new Clientes();
        CT.setNombre(nombre);

        String claveEncriptada = new BCryptPasswordEncoder().encode(clave);
        CT.setClave(claveEncriptada);

        CT.setDni(dni);
        CT.setCorreo(correo);
        CT.setRole(Role.CLIENTE);
        return repositorioClientes.save(CT);
    }

    @Transactional
    public Clientes edit(String id, String clave, String clave2, String nombre, Long dni, String correo) throws Exception {
        this.Validator(nombre, clave, clave2, dni, correo);
        Optional<Clientes> respuesta = repositorioClientes.findById(id);
        if (respuesta.isPresent()) {
            Clientes CT = respuesta.get();
            CT.setNombre(nombre);
            CT.setDni(dni);
            CT.setCorreo(correo);
            String claveEncriptada = new BCryptPasswordEncoder().encode(clave);
            CT.setClave(claveEncriptada);
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
    public List<Clientes> findAll() throws Exception {
        return repositorioClientes.findAll();
    }

    @Transactional(readOnly = true)
    public Clientes findById(String id) throws Exception {
        return repositorioClientes.getById(id);
    }

    @Transactional(readOnly = true)
    public Clientes buscarPorDNI(Long dni) throws Exception {
        return repositorioClientes.buscarPorDNI(dni);

    }

    @Transactional
    public void cambiarRol(String id) throws Exception {
        Optional<Clientes> respuesta = repositorioClientes.findById(id);
        if (respuesta.isPresent()) {
            Clientes cliente = respuesta.get();
            if (cliente.getRole().equals(Role.CLIENTE)) {
                cliente.setRole(Role.ADMIN);
            } else if (cliente.getRole().equals(Role.ADMIN)) {
                cliente.setRole(Role.CLIENTE);
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Clientes cliente = repositorioClientes.buscarPorMail(correo);
        if (cliente != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority permiso1 = new SimpleGrantedAuthority("ROLE_" + cliente.getRole());
            permisos.add(permiso1);

            //Guarda la session de usuario. el usuario lo guarda en "usuariosession"
            //session guarda los datos de "usuariosession" y se puede manipular en html/thymeleaf
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", cliente);

            User user = new User(cliente.getCorreo(), cliente.getClave(), permisos);
            return user;
        } else {
            return null;
        }
    }

}
