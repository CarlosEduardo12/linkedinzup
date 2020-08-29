package com.carlos.linkedinzup.api.controller.vaga.dto;

import lombok.Data;

@Data
public class ResponseVagaDto {
    private Long id;
    private String cargo;
    private String disponivel;
    private String status;
    private ResponseEmpresaDto empresa;
}
