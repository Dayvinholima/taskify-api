package com.taskify.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskify.api.model.Usuario;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findByEmail(String email);
    Optional<List<Usuario>> findByNome(String nome);
}
