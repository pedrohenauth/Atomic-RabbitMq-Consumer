package com.orch.stock.service;

import com.orch.stock.domain.Cadastro;
import com.orch.stock.infrastructure.rabbitConsumer.database.CadastroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CadastroService {

    private final CadastroRepository cadastroRepository;

    public void enviarMensagem(Cadastro cadastro){
        log.info("..: Persistindindo cadastro na base de dados ({}) :..", cadastro);
        cadastroRepository.enviarMensagem(cadastro);
        log.info("..: Cadastro persistido na base de dados ({}) :..", cadastro);
    }
}
