package com.carlos.linkedinzup.api.controller.vaga.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestEmpresaId {

    @NotNull
    private Long id;
}
