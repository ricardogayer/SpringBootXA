package com.mwave.Proposta;

import com.mwave.Proposta.oracle.entity.Cliente;
import com.mwave.Proposta.oracle.service.ClienteService;
import com.mwave.Proposta.postgresql.entity.Proposta;
import com.mwave.Proposta.postgresql.service.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class APIController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PropostaService propostaService;

    @PostMapping("cliente")
    public Cliente postCliente(@RequestBody Cliente cliente) {
        return clienteService.incluirCLiente(cliente);
    }

    @PostMapping("proposta")
    public Proposta postProposta(@RequestBody Proposta proposta) {
        return propostaService.incluirProposta(proposta);
    }

    @PostMapping("xa/{id}")
    @Transactional(value = "chainedTransactionManager", rollbackFor = Exception.class)
    public String executaTransacao(@PathVariable("id") Long id) throws Exception {

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome("Daniel Gayer");

        Proposta proposta = new Proposta();
        proposta.setId(id);
        proposta.setDescricao("Seguro de Vida em Grupo");

        propostaService.incluirProposta(proposta);
        clienteService.incluirCLiente(cliente);

        if (id >= 400) {
            throw new Exception("Rollback na manivela...");
        }

        return "sucesso";
    }

}
