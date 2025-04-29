package com.empresa.sistema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.empresa.sistema.models.Usuario;
import com.empresa.sistema.repositories.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    
    
    @GetMapping("/cadastro")
    public String userForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String newUser(@ModelAttribute Usuario usuario) {
        repository.save(usuario);        
        return "redirect:/login";
    }
    
    @GetMapping("/area-logada")
    public String areaLogada(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("usuarioLogado", user.getUsername());
        return "area-logada";
    }
    

    
}
