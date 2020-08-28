package com.carlos.linkedinzup.service;

import com.carlos.linkedinzup.exception.NegocioException;
import com.carlos.linkedinzup.model.Empresa;
import com.carlos.linkedinzup.repository.EmpresaRepository;
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

    public void excluir(Long id){
        empresaRepository.deleteById(id);
    }
}
