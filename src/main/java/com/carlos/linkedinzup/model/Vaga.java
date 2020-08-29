package com.carlos.linkedinzup.model;

import com.carlos.linkedinzup.model.ValidationGroups.ValidationGroups;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

@Entity
@NoArgsConstructor
@Data
@Table(name = "vagas")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String cargo;

    private int disponivel;

    @Enumerated(EnumType.STRING)
    private StatusVagas status;

    @Valid
    @ConvertGroup(to = ValidationGroups.EmpresaId.class)
    @NotNull
    @ManyToOne
    private Empresa empresa;
}
