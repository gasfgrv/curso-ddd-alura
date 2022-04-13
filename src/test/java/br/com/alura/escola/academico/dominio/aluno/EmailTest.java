package br.com.alura.escola.academico.dominio.aluno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

    @Test
    void naoDeveriaCriarEmailsComEnderecosInvalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email(null));
        assertThrows(IllegalArgumentException.class,
                () -> new Email(""));
        assertThrows(IllegalArgumentException.class,
                () -> new Email("qualquercoisa"));
    }

    @Test
    void deveriaCriarUmEmail() {
        assertDoesNotThrow(() -> new Email("nicoledaconceicao83@leanclean.com.br"));
    }
}