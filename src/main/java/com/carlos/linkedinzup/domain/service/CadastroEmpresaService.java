package com.carlos.linkedinzup.domain.service;

import com.carlos.linkedinzup.domain.exception.NegocioException;
import com.carlos.linkedinzup.domain.model.Empresa;
import com.carlos.linkedinzup.domain.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastroEmpresaService {

    private final EmpresaRepository empresaRepository;

    public CadastroEmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa salvar(Empresa empresa) {
        Empresa empresaExistente = empresaRepository.findByCnpj(empresa.getCnpj());

        if (empresaExistente != null && !empresaExistente.equals(empresa)){
            throw new NegocioException("Já existe uma empresa cadastrada com este CNPJ.");
        }
        return empresaRepository.save(empresa);
    }

    public Empresa editar(Empresa empresa){
        Empresa empresaExistente = empresaRepository.findByCnpj(empresa.getCnpj());
        if (empresaExistente != null && empresaExistente.getCnpj().equals(empresa.getCnpj()) &&
                !empresaExistente.getId().equals(empresa.getId())){
            throw new NegocioException("Já existe uma empresa cadastrada com este CNPJ.");
        }
        return empresaRepository.save(empresa);
    }

    public void excluir(Long id){
        empresaRepository.deleteById(id);
    }
}
