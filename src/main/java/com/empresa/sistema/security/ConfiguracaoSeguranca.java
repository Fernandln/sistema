package com.empresa.sistema.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.empresa.sistema.models.Usuario;

import com.empresa.sistema.repositories.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca {
    

    @Bean
    public UserDetailsService  userDetailsService(UsuarioRepository usuarioRepository){
        
        return username -> {
            Usuario usuario = usuarioRepository.findByNome(username).orElseThrow(() -> new UsernameNotFoundException("Usuario Não encontrado!! 😒 "));

            return new User(
                usuario.getNome(),
                usuario.getSenha(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
                )
        }


    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(req -> {
            req.requestMatchers("/", "/cadastro" ).permitAll().anyRequest().authenticated();//não sei se esse ponto e virgula ta certo
        }).formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/area-logada", true).permitAll())
        .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll())
        .build();
    }
}
