package com.empresa.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.sistema.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    
}
