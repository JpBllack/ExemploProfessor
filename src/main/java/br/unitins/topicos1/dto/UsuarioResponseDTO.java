package br.unitins.topicos1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.unitins.topicos1.model.Sexo;
import br.unitins.topicos1.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String cpf,
    String nome,
    String email,
    String login,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Sexo sexo
) {
    public UsuarioResponseDTO(Usuario u) {
        this(u.getId(), 
             u.getPessoaFisica().getCpf(), 
             u.getPessoaFisica().getNome(),
             u.getPessoaFisica().getEmail(),
             u.getLogin1(), 
             u.getPessoaFisica().getSexo()
        ); 
    }


}
