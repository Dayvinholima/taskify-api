package com.taskify.api.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskify.api.model.Tarefa;
import com.taskify.api.repository.TarefaRepository;

@RestController
@RequestMapping(value = "/tarefas")

public class TarefaController {
    @PostMapping
    public @ResponseBody Tarefa cadastrarTarefa(@RequestBody Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable("id") Long id, @RequestBody Tarefa tarefa){
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);

        if(tarefaExistente.isPresent()){
            Tarefa tarefaObj = tarefaExistente.get();

            tarefaObj.setNome(tarefa.getNome());
            tarefaObj.setDataDeCriacao(tarefa.getDataDeCriacao());
            tarefaObj.setDataDeConclusao(tarefa.getDataDeConclusao());
            tarefaObj.setPrioridade(tarefa.getPrioridade());
            tarefaObj.setStatus(tarefa.getStatus());
            tarefaObj.setUsuario(tarefa.getUsuario());
            tarefaObj.setProjeto(tarefa.getProjeto());

            return tarefaRepository.save(tarefaObj);
        }
            return null;
    }

    @GetMapping
    public List<Tarefa> listaTarefas(){
        return tarefaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional <Tarefa> obterTarefaPeloId(@PathVariable("id") Long id){
        return tarefaRepository.findById(id);
    }
    @DeleteMapping("/{id}")
    public void deletarTarefaPeloId(@PathVariable("id") Long id){
        tarefaRepository.deleteById(id);

    }

    @GetMapping("/usuario/{id}")
    public Optional<List<Tarefa>> obterTarefasDeUmUsuario(@PathVariable("id") Long IdUsuario){
        return tarefaRepository.findByUsuario(IdUsuario);
    }
    
    @GetMapping("/projeto/{id}")
    public Optional<List<Tarefa>> ObterTarefasDeUmProjeto(@PathVariable("id") Long IdProjeto){
        return tarefaRepository.findByProjeto(IdProjeto);
    }

    @Autowired
    private TarefaRepository tarefaRepository;
    
}
