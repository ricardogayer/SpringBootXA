package com.mwave.Proposta.oracle.service;

import com.mwave.Proposta.oracle.entity.Cliente;
import com.mwave.Proposta.oracle.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente incluirCLiente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

}
