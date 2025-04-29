package com.empresa.sistema.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.sistema.models.Usuario;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNome(String nome);
    // Optional -> retorna uma proteção contra dados "Null".
    
    
}
