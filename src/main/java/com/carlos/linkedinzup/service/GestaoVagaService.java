package com.carlos.linkedinzup.service;

import com.carlos.linkedinzup.exception.NegocioException;
import com.carlos.linkedinzup.model.Empresa;
import com.carlos.linkedinzup.model.StatusVagas;
import com.carlos.linkedinzup.model.Vaga;
import com.carlos.linkedinzup.repository.EmpresaRepository;
import com.carlos.linkedinzup.repository.VagaRepository;
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
