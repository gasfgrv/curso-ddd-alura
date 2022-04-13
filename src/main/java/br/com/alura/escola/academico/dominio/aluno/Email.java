package br.com.alura.escola.academico.dominio.aluno;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Email implements Serializable {

    private String endereco;

    public Email() {
    }

    public Email(String endereco) {
        if (endereco == null ||
                !endereco.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("E-mail invalido!");
        }

        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

}