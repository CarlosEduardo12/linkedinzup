package com.carlos.linkedinzup.api.controller.vaga.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RequestVagaDto {

    @NotBlank
    private String cargo;

    @NotBlank
    private int disponivel;

    @Valid
    @NotNull
    private RequestEmpresaId empresa;
}
