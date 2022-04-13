package br.com.alura.escola.academico.dominio.aluno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CPFTest {

    @Test
    void naoDeveriaCriarUmCpfComNumeroInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new CPF(null));
        assertThrows(IllegalArgumentException.class,
                () -> new CPF(""));
        assertThrows(IllegalArgumentException.class,
                () -> new CPF("qualquercoisa"));
        assertThrows(IllegalArgumentException.class,
                () -> new CPF("12345678910"));
    }

    @Test
    void deveriaCriarUmCpf() {
        assertDoesNotThrow(() -> new CPF("123.456.789-10"));
    }
}