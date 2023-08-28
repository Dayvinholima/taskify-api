package com.taskify.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.taskify.api.model.Projeto;
import com.taskify.api.repository.ProjetoRepository;

@RestController
@RequestMapping(value = "/projetos")
public class ProjetoController {


    @PostMapping
    public @ResponseBody Projeto cadastrarProgeto(@RequestBody Projeto projeto){
        return projetoRepository.save(projeto);
    }

    @PutMapping ("/{id}")
    public Projeto atualizaProjeto(@PathVariable("id")Long id, @RequestBody Projeto projeto){
        Optional<Projeto> projetoExistente = projetoRepository.findById(id);

        if( projetoExistente.isPresent()){
            Projeto projetoObj = projetoExistente.get();

            projetoObj.setNome(projeto.getNome());
            projetoObj.setDecricao(projeto.getDecricao());
            projetoObj.setUsuario(projeto.getUsuario());

        return projetoRepository.save(projetoObj);

        }
        return null;
    }

    @GetMapping
    public List<Projeto> listarProjetos(){
        return projetoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional <Projeto> obterProjetoPeloId(@PathVariable("id") Long id){
        return projetoRepository.findById(id);
    }
    @DeleteMapping("/{id}")
    public void deletarProjetoPeloId(@PathVariable("id") Long id){
        projetoRepository.deleteById(id);
    
    }

    @GetMapping("/usuario/{id}")
    public Optional<List<Projeto>> obterProjetosDeUmUsuario(@PathVariable("id")Long IdUsuario) {
        return projetoRepository.findByUsuario(IdUsuario);
    }


    @Autowired
    private ProjetoRepository projetoRepository;
    
}