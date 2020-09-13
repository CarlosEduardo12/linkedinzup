package com.carlos.linkedinzup.domain.repository;

import com.carlos.linkedinzup.domain.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VagaRepository  extends JpaRepository<Vaga, Long> {
    @Query(value = "SELECT * FROM Vagas WHERE cargo = ?1 AND empresa_Id = ?2", nativeQuery = true)
    Optional<Vaga> findByCargoAndEmpresa(String cargo, Long id);

}
