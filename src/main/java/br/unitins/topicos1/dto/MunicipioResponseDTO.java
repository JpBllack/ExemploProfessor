package br.unitins.topicos1.dto;

import java.util.HashMap;
import java.util.Map;

import br.unitins.topicos1.model.Municipio;

public class MunicipioResponseDTO {

    private Long id;
    private String nome;
    private Map<String, Object> estado;

    public MunicipioResponseDTO(Municipio municipio) {
        this.id = municipio.getId();
        this.nome = municipio.getNome();
        this.estado = new HashMap<String, Object>();
        this.estado.put("nome", municipio.getEstado().getNome());
        this.estado.put("sigla", municipio.getEstado().getSigla());
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

    public Map<String, Object> getEstado() {
        return estado;
    }

    public void setEstado(Map<String, Object> estado) {
        this.estado = estado;
    }

    
    
}
