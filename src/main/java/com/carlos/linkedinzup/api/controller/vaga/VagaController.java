package com.carlos.linkedinzup.api.controller.vaga;

import com.carlos.linkedinzup.api.controller.vaga.dto.RequestVagaDto;
import com.carlos.linkedinzup.api.controller.vaga.dto.ResponseVagaDto;
import com.carlos.linkedinzup.domain.model.Vaga;
import com.carlos.linkedinzup.domain.repository.VagaRepository;
import com.carlos.linkedinzup.domain.service.GestaoVagaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    private final VagaRepository vagaRepository;
    private final GestaoVagaService gestaoVaga;
    private final ModelMapper modelMapper;

    public VagaController(VagaRepository vagaRepository, GestaoVagaService gestaoVaga, ModelMapper modelMapper) {
        this.vagaRepository = vagaRepository;
        this.gestaoVaga = gestaoVaga;
        this.modelMapper = modelMapper;
    }
    @GetMapping
    public List<ResponseVagaDto> listar(){
        return toCollectionModel(vagaRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseVagaDto>buscar(@PathVariable Long id){
        Optional<Vaga> vaga = vagaRepository.findById(id);
        if (vaga.isPresent()){
            ResponseVagaDto vagaModel = toModel(vaga.get());
            return ResponseEntity.ok(vagaModel);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseVagaDto adicionar(@Valid @RequestBody RequestVagaDto requestVagaDto){
        Vaga vaga = ToEntity(requestVagaDto);
        return toModel(gestaoVaga.criar(vaga));
    }

    @PutMapping("{id}")
    public ResponseEntity<Vaga> atualizar(@PathVariable Long id, @Valid @RequestBody RequestVagaDto requestVagaDto){
        if (!vagaRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Vaga vaga = ToEntity(requestVagaDto);
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

    private ResponseVagaDto toModel(Vaga vaga){
        return modelMapper.map(vaga, ResponseVagaDto.class);

    }

    private List<ResponseVagaDto> toCollectionModel(List<Vaga> vaga){
        return vaga.stream().map(this::toModel).collect(Collectors.toList());
    }

    private Vaga ToEntity(RequestVagaDto requestVagaDto){
        return modelMapper.map(requestVagaDto, Vaga.class);
    }
}
