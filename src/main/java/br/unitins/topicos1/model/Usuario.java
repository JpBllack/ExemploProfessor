package br.unitins.topicos1.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Usuario extends DefaultEntity {

    private String login1;
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

    public String getLogin1() {
        return login1;
    }

    public void setLogin1(String login) {
        this.login1 = login1;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

}
