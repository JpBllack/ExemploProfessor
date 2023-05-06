package br.unitins.topicos1.service;

import br.unitins.topicos1.model.Usuario;

public interface TokenJwtService {
    public String generateJwt(Usuario usuario);
}
