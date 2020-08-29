package com.carlos.linkedinzup.controller;

import com.carlos.linkedinzup.model.Vaga;
import com.carlos.linkedinzup.repository.VagaRepository;
import com.carlos.linkedinzup.service.GestaoVagaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    private final VagaRepository vagaRepository;

    private final GestaoVagaService gestaoVaga;

    public VagaController(VagaRepository vagaRepository, GestaoVagaService gestaoVaga) {
        this.vagaRepository = vagaRepository;
        this.gestaoVaga = gestaoVaga;
    }
    @GetMapping
    public List<Vaga> listar(){
        return vagaRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Vaga>buscar(@PathVariable Long id){
        Optional<Vaga> vaga = vagaRepository.findById(id);
        return vaga.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vaga adicionar(@Valid @RequestBody Vaga vaga){
        return gestaoVaga.criar(vaga);
    }

    @PutMapping("{id}")
    public ResponseEntity<Vaga> atualizar(@PathVariable Long id, @Valid @RequestBody Vaga vaga){
        if (!vagaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        vaga.setId(id);
        gestaoVaga.criar(vaga);
        return ResponseEntity.ok(vaga);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        if (!vagaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        gestaoVaga.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
