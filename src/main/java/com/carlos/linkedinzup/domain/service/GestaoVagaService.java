package com.carlos.linkedinzup.domain.service;

import com.carlos.linkedinzup.domain.exception.NegocioException;
import com.carlos.linkedinzup.domain.model.Vaga;

public interface GestaoVagaService {
    Vaga criar(Vaga vaga) throws NegocioException;
    void excluir(Long id);
}
