package com.carlos.linkedinzup.api.controller.empresa;

import com.carlos.linkedinzup.domain.model.Empresa;
import com.carlos.linkedinzup.domain.repository.EmpresaRepository;
import com.carlos.linkedinzup.domain.service.CadastroEmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaRepository empresaRepository;
    private final CadastroEmpresaService cadastroEmpresa;

    public EmpresaController(EmpresaRepository empresaRepository, CadastroEmpresaService cadastroEmpresa) {
        this.empresaRepository = empresaRepository;
        this.cadastroEmpresa = cadastroEmpresa;
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
    public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @Valid @RequestBody Empresa empresa){
        if (!empresaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        empresa.setId(id);
        cadastroEmpresa.salvar(empresa);
        return ResponseEntity.ok(empresa);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        if (!empresaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        cadastroEmpresa.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
