package br.unitins.topicos1.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Usuario extends DefaultEntity {

    private String login;
    private String senha;

    @OneToOne
    @JoinColumn(name = "id_telefone_celular", unique = true)
    private Telefone celular;

    @OneToOne
    @JoinColumn(name = "id_telefone_whatsapp", unique = true)
    private Telefone whastapp;

    @OneToMany(mappedBy = "usuario")
    private List<Endereco> listaEndereco;

    @OneToOne
    @JoinColumn(name = "id_pessoa_fisica", unique = true)
    private PessoaFisica pessoaFisica;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Telefone getCelular() {
        return celular;
    }

    public void setCelular(Telefone celular) {
        this.celular = celular;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

}
