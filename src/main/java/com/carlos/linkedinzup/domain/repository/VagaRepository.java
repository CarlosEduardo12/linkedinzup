package com.carlos.linkedinzup.domain.repository;

import com.carlos.linkedinzup.domain.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository  extends JpaRepository<Vaga, Long> {
    Vaga findByCargo(String cargo);

    @Query(value = "SELECT * FROM Vagas WHERE cargo = ?1 AND id = ?2", nativeQuery = true)
    Vaga findByCargoAndEmpresa(String cargo, Long id);

}
