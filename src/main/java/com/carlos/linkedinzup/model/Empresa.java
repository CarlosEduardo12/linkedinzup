package com.carlos.linkedinzup.model;

import com.carlos.linkedinzup.model.ValidationGroups.ValidationGroups;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
@Table(name = "empresas")
public class Empresa {

    @NotNull(groups = ValidationGroups.EmpresaId.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @CNPJ
    @Size(max = 18)
    private String cnpj;
    @NotBlank
    private String localidade;

}
