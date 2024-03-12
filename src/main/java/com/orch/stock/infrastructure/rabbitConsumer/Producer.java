package com.orch.stock.infrastructure.rabbitConsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orch.stock.domain.Cadastro;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@Slf4j
@RestController
public class Producer{

    private final AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public Producer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @SneakyThrows
    @PostMapping(value = "/envio")
    public String enviarMensagemFila(){

        String queueName = "mensageria-teste";
        Cadastro cadastro = new Cadastro();
        cadastro.setNome("Jhony");
        cadastro.setCpf("12021023443");
        cadastro.setAnoNascimento("23/10/1997");
        cadastro.setProfissao("Analista de TI");
        cadastro.setTelefone("81995279292");
        cadastro.setEndereco("Rua Delmiro Monteiro");

        try{
            amqpTemplate.convertAndSend("mensageria-teste", objectMapper.writeValueAsString(cadastro));
            return "Cadastramento realizado";
        } catch (Exception e){
            log.info("..: Ocorreu um erro ao enviar mensagem para fila. :..");
            throw new Exception("..: Ocorreu um erro ao enviar mensagem para fila :..");
        }
    }


}
