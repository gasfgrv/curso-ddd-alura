package br.com.alura.escola.academico.dominio.aluno;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Telefone implements Serializable {

    private String ddd;
    @Id
    private String numero;

    public Telefone() {
    }

    public Telefone(String ddd, String numero) {
        if (!idDddValido(ddd) || !isNumeroValido(numero)) {
            throw new IllegalArgumentException("Telefone inváldo");
        }

        this.ddd = ddd;
        this.numero = numero;
    }

    private boolean isNumeroValido(String numero) {
        return numero != null && numero.matches("\\d{4,5}-\\d{4}");
    }

    private boolean idDddValido(String ddd) {
        return ddd != null && ddd.matches("\\d{2}");
    }

    public String getDdd() {
        return ddd;
    }

    public String getNumero() {
        return numero;
    }


}
