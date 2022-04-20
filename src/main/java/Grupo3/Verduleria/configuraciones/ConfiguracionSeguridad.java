package Grupo3.Verduleria.configuraciones;



import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter{
    
//    @Autowired
//    private UsuarioServicio servicioUsuario;
//    
//	//metodo que indica que en el servicio se usara metodos de seguridad(en este caso encriptado)
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(servicioUsuario).passwordEncoder(new BCryptPasswordEncoder());
//    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/css/*","/js/*","/img/*","/**").permitAll()
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("username")		
                .passwordParameter("password")
                .defaultSuccessUrl("/inicio").permitAll()
            .and().logout() 
                .logoutUrl("/logout")  		
                .logoutSuccessUrl("/").permitAll()	
            .and().csrf().disable();
    }
}
