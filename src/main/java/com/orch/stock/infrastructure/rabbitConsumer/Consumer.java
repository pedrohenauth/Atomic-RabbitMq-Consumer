package com.orch.stock.infrastructure.rabbitConsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orch.stock.domain.Cadastro;
import com.orch.stock.service.CadastroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {

    @Autowired
    private CadastroService cadastroService;

    @RabbitListener(queues = "mensageria-teste")
    public void consumindoMensagem(String message) throws Exception {
        try{
            log.info("..: Recebida mensagem: '{}' :..", message);
            ObjectMapper objectMapper = new ObjectMapper();
            var mensagemConvertida = objectMapper.readValue(message, Cadastro.class);
            log.info("..: Mensagem convertida em objeto do tipo Mensagem: '{}' :..", mensagemConvertida);
            log.info("..: Iniciando cadastramento :..");
            cadastroService.enviarMensagem(mensagemConvertida);
            log.info("..: Fluxo de gravação finalizado :..");
        }catch (Exception e){
            throw new Exception("..: Erro ao realizar o cadastramento :..");
        }
    }
}
