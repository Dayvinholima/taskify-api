package com.taskify.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taskify.api.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>  {
    
    @Query("SELECT p FROM tb_tarefas p WHERE p.projeto.idProjeto = : idProjeto")//pegando pelo id do projeto
    Optional<List<Tarefa>> findByProjeto(Long projeto);

    @Query("SELECT t FROM tb_tarefas t WHERE t.usuario.idUsuario = : idUsuario") //pegando pelo id dousuario
    Optional<List<Tarefa>> findByUsuario(Long usuario);
}
