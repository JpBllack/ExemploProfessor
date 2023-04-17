package br.unitins.topicos1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.unitins.topicos1.model.PessoaFisica;
import br.unitins.topicos1.model.Sexo;

public record PessoaFisicaResponseDTO(
    Long id,
    String cpf,
    String nome,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Sexo sexo
) {
    public PessoaFisicaResponseDTO(PessoaFisica pf) {
        this(pf.getId(), pf.getCpf(), pf.getNome(), pf.getSexo()); 
    }


}
