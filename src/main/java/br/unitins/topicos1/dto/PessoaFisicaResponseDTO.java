package br.unitins.topicos1.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.unitins.topicos1.model.PessoaFisica;
import br.unitins.topicos1.model.Sexo;

public class PessoaFisicaResponseDTO {

    private Long id;
    private String cpf;
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Sexo sexo;

    public PessoaFisicaResponseDTO(PessoaFisica pessoaFisica) {
        this.id = pessoaFisica.getId();
        this.cpf = pessoaFisica.getCpf();
        this.nome = pessoaFisica.getNome();
        this.sexo = pessoaFisica.getSexo();
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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    

}
