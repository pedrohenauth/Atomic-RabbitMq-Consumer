package com.orch.stock.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Cadastro {

    @JsonProperty(value = "nome")
    private String nome;
    @JsonProperty(value = "cpf")
    private String cpf;
    @JsonProperty(value = "anoNascimento")
    private String anoNascimento;
    @JsonProperty(value = "profissao")
    private String profissao;
    @JsonProperty(value = "telefone")
    private String telefone;
    @JsonProperty(value = "endereco")
    private String endereco;

}
