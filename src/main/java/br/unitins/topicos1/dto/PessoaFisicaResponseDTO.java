package br.unitins.topicos1.dto;

import java.util.HashMap;
import java.util.Map;

import br.unitins.topicos1.model.PessoaFisica;

public class PessoaFisicaResponseDTO {

    private Long id;
    private String cpf;
    private String nome;

    public PessoaFisicaResponseDTO(PessoaFisica pessoaFisica) {
        this.id = pessoaFisica.getId();
        this.cpf = pessoaFisica.getCpf();
        this.nome = pessoaFisica.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
