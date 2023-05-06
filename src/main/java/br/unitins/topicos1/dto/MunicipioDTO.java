package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MunicipioDTO {

    @NotBlank(message = "O campo nome deve ser informado.")
    private String nome;
    
    @NotNull(message = "O campo idEstado deve ser informado.")
    private Long idEstado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

}
