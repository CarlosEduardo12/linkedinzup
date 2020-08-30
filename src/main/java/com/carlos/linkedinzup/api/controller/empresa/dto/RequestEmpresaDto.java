package com.carlos.linkedinzup.api.controller.empresa.dto;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;

@Data
public class RequestEmpresaDto {

    @NotBlank
    private String nome;

    @CNPJ
    @NotBlank
    private String cnpj;

    @NotBlank
    private String localidade;
}
