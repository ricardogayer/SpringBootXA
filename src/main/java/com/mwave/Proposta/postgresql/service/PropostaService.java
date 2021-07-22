package com.mwave.Proposta.postgresql.service;

import com.mwave.Proposta.postgresql.entity.Proposta;
import com.mwave.Proposta.postgresql.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    public Proposta incluirProposta(Proposta proposta) {
        return propostaRepository.save(proposta);
    }

}
