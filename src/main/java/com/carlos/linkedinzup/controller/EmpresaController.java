package com.carlos.linkedinzup.controller;

import com.carlos.linkedinzup.model.Empresa;
import com.carlos.linkedinzup.repository.EmpresaRepository;
import com.carlos.linkedinzup.service.CadastroEmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private final EmpresaRepository empresaRepository;

    @Autowired
    private CadastroEmpresaService cadastroEmpresa;


    public EmpresaController(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }
    @GetMapping
    public List<Empresa> listar(){
        return empresaRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Empresa> buscar(@PathVariable Long id){
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa adicionar(@Valid @RequestBody Empresa empresa){
        return cadastroEmpresa.salvar(empresa);
    }

    @PutMapping("{id}")
    public ResponseEntity<Empresa> atualizar(@Valid @PathVariable Long id, @RequestBody Empresa empresa){
        if (!empresaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        empresa.setId(id);
        cadastroEmpresa.salvar(empresa);
        return ResponseEntity.ok(empresa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        if (!empresaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        cadastroEmpresa.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
