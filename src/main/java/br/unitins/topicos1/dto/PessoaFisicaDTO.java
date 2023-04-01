package br.unitins.topicos1.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PessoaFisicaDTO {

    @NotBlank(message = "O campo cpf deve ser informado.")
    @Size(max = 14, message = "O cpf deve posssuir no máximo 14 caracteres.")
    private String cpf;

    private Integer sexo;

    @NotBlank(message = "O campo nome deve ser informado.")
    private String nome;

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

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

}
