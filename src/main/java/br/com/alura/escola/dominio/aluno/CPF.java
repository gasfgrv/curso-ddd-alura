package br.com.alura.escola.dominio.aluno;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CPF implements Serializable {

    private String numero;

    public CPF() {
    }

    public CPF(String numero) {
        if (numero == null ||
                !numero.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
            throw new IllegalArgumentException("CPF invalido!");
        }
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

}