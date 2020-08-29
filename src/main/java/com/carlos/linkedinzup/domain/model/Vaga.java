package com.carlos.linkedinzup.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "vagas")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cargo;
    private int disponivel;

    @Enumerated(EnumType.STRING)
    private StatusVagas status;

    @ManyToOne
    private Empresa empresa;
}
