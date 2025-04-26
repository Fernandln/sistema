package com.empresa.sistema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.empresa.sistema.models.Usuario;
import com.empresa.sistema.repositories.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/cadastrar")
    public String userForm(Model model) {
        model.addAttribute("usuarios", new Usuario());
        return "usuario/cadastrar_usuario";
    }

    @PostMapping("/cadastrar")
    public String newUser(Usuario usuario) {
        repository.save(usuario);        
        return "redirect:/";
    }
    

    
}
