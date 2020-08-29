package com.carlos.linkedinzup.domain.service;

import com.carlos.linkedinzup.domain.exception.NegocioException;
import com.carlos.linkedinzup.domain.model.Empresa;
import com.carlos.linkedinzup.domain.model.StatusVagas;
import com.carlos.linkedinzup.domain.model.Vaga;
import com.carlos.linkedinzup.domain.repository.EmpresaRepository;
import com.carlos.linkedinzup.domain.repository.VagaRepository;
import org.springframework.stereotype.Service;

@Service
public class GestaoVagaService {

    private final VagaRepository vagaRepository;

    private final EmpresaRepository empresaRepository;

    public GestaoVagaService(VagaRepository vagaRepository, EmpresaRepository empresaRepository) {
        this.vagaRepository = vagaRepository;
        this.empresaRepository = empresaRepository;
    }

    public Vaga criar(Vaga vaga) {
        Vaga cargoAndEmpresa = vagaRepository.findByCargoAndEmpresa(vaga.getCargo(), vaga.getEmpresa().getId());
        Empresa empresa = empresaRepository.findById(vaga.getEmpresa().getId())
                .orElseThrow(() -> new NegocioException("Empresa não encontrada"));

        vaga.setEmpresa(empresa);
        if(vaga.getDisponivel() == 0){
            vaga.setStatus(StatusVagas.fechada);
        }
        else{
            vaga.setStatus(StatusVagas.aberta);
        }
        if (cargoAndEmpresa.getCargo().equals(vaga.getCargo()) && cargoAndEmpresa.getDisponivel() == vaga.getDisponivel()
                && cargoAndEmpresa.getEmpresa().equals(vaga.getEmpresa())){
            throw new NegocioException("Já existe uma cargo cadastrado com este nome para essa empresa.");
        }
        return vagaRepository.save(vaga);
    }

    public void excluir(Long id){
        vagaRepository.deleteById(id);
    }
}
