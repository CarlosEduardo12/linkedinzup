package com.carlos.linkedinzup.api.controller.empresa;

import com.carlos.linkedinzup.api.controller.empresa.dto.RequestEmpresaDto;
import com.carlos.linkedinzup.api.controller.empresa.dto.ResponseEmpresaDto;
import com.carlos.linkedinzup.domain.model.Empresa;
import com.carlos.linkedinzup.domain.repository.EmpresaRepository;
import com.carlos.linkedinzup.domain.service.CadastroEmpresaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaRepository empresaRepository;
    private final CadastroEmpresaService cadastroEmpresa;
    private final ModelMapper modelMapper;

    public EmpresaController(EmpresaRepository empresaRepository, CadastroEmpresaService cadastroEmpresa, ModelMapper modelMapper) {
        this.empresaRepository = empresaRepository;
        this.cadastroEmpresa = cadastroEmpresa;
        this.modelMapper = modelMapper;
    }
    @GetMapping
    public List<ResponseEmpresaDto> listar(){
        return toCollectionModel(empresaRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseEmpresaDto> buscar(@PathVariable Long id){
        Optional<Empresa> empresa = empresaRepository.findById(id);
        if (empresa.isPresent()){
            ResponseEmpresaDto empresaModel = toDto(empresa.get());
            return ResponseEntity.ok(empresaModel);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEmpresaDto adicionar(@Valid @RequestBody RequestEmpresaDto requestEmpresaDto){
        Empresa empresa = toEntity(requestEmpresaDto);
        return toDto(cadastroEmpresa.salvar(empresa));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseEmpresaDto> atualizar(@PathVariable Long id, @Valid @RequestBody RequestEmpresaDto requestEmpresaDto){
        if (!empresaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Empresa empresa = toEntity(requestEmpresaDto);
        empresa.setId(id);
        return ResponseEntity.ok(toDto(cadastroEmpresa.editar(empresa)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        if (!empresaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        cadastroEmpresa.excluir(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEmpresaDto toDto(Empresa empresa){
        return modelMapper.map(empresa, ResponseEmpresaDto.class);
    }

    private List<ResponseEmpresaDto> toCollectionModel(List<Empresa> empresa){
        return empresa.stream().map(this::toDto).collect(Collectors.toList());
    }

    private Empresa toEntity(RequestEmpresaDto requestEmpresaDto){
        return modelMapper.map(requestEmpresaDto, Empresa.class);
    }
}
