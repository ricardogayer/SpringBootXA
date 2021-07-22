package com.mwave.Proposta.postgresql.repository;

import com.mwave.Proposta.postgresql.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta,Long> {
}
