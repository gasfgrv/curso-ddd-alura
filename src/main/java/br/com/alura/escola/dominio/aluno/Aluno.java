package br.com.alura.escola.dominio.aluno;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Aluno implements Serializable {

    @EmbeddedId
    private CPF cpf;
    private String nome;
    @Embedded
    private Email email;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_cpf")
    private final List<Telefone> telefones = new ArrayList<>();

    private String senha;

    public Aluno() {
    }

    public Aluno(CPF cpf, String nome, Email email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public void adicionarTelefone(String ddd, String numero) {
        if (telefones.size() == 2) {
            throw new MaximoDeTelefonesAtingindoException("Número máximo de telefones atingindo");
        }

        this.telefones.add(new Telefone(ddd, numero));
    }

    public String getCpf() {
        return cpf.getNumero();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email.getEndereco();
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

}





