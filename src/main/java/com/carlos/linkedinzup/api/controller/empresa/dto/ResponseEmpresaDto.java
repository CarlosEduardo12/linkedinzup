package com.carlos.linkedinzup.api.controller.empresa.dto;

import lombok.Data;

@Data
public class ResponseEmpresaDto {

    private Long id;
    private String nome;
    private String cnpj;
    private String localidade;
}
