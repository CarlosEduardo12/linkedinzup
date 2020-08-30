package com.carlos.linkedinzup.domain.service;

import com.carlos.linkedinzup.domain.exception.NegocioException;
import com.carlos.linkedinzup.domain.model.Empresa;

public interface CadastroEmpresaService {
    Empresa salvar(Empresa empresa) throws NegocioException;
    Empresa editar(Empresa empresa) throws NegocioException;
    void excluir(Long id);

}
