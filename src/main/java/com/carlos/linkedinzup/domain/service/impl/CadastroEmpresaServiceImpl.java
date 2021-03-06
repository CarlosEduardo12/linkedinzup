package com.carlos.linkedinzup.domain.service.impl;

import com.carlos.linkedinzup.domain.exception.NegocioException;
import com.carlos.linkedinzup.domain.model.Empresa;
import com.carlos.linkedinzup.domain.repository.EmpresaRepository;
import com.carlos.linkedinzup.domain.service.CadastroEmpresaService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroEmpresaServiceImpl implements CadastroEmpresaService {

    private final EmpresaRepository empresaRepository;

    public CadastroEmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }
    @Override
    public Empresa salvar(Empresa empresa) {
        Optional<Empresa> empresaExistente = empresaRepository.findByCnpj(empresa.getCnpj());

        if (empresaExistente.isPresent()){
            throw new NegocioException("Já existe uma empresa cadastrada com este CNPJ.");
        }
        return empresaRepository.save(empresa);
    }
    @Override
    public Empresa editar(Empresa empresa){
        Optional<Empresa> empresaExistente = empresaRepository.findByCnpj(empresa.getCnpj());
        if (empresaExistente.isPresent() && empresaExistente.get().getCnpj().equals(empresa.getCnpj()) &&
                !empresaExistente.get().getId().equals(empresa.getId())){
            throw new NegocioException("Já existe uma empresa cadastrada com este CNPJ.");
        }
        return empresaRepository.save(empresa);
    }
    @Override
    public void excluir(Long id){
        empresaRepository.deleteById(id);
    }
}
