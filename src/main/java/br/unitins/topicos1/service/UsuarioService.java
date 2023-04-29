package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.UsuarioResponseDTO;

public interface UsuarioService {

    // recursos basicos
    List<UsuarioResponseDTO> getAll();

    UsuarioResponseDTO findById(Long id);

  //  UsuarioResponseDTO create(UsuarioDTO UsuarioDTO);

   // UsuarioResponseDTO update(Long id, UsuarioDTO UsuarioDTO);

    void delete(Long id);

    // recursos extras

    List<UsuarioResponseDTO> findByNome(String nome);

    long count();

}
