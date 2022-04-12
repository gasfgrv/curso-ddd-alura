package br.com.alura.escola.dominio.aluno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TelefoneTest {

    @Test
    void naoDeveriaCriarUmTelefoneComDddInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Telefone(null, "3975-6740"));
        assertThrows(IllegalArgumentException.class,
                () -> new Telefone("", "3975-6740"));
        assertThrows(IllegalArgumentException.class,
                () -> new Telefone("1", "3975-6740"));
        assertThrows(IllegalArgumentException.class,
                () -> new Telefone("111", "3975-6740"));
        assertThrows(IllegalArgumentException.class,
                () -> new Telefone("ddd", "3975-6740"));
    }

    @Test
    void naoDeveriaCriarUmTelefoneComNumeroInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Telefone("77", null));
        assertThrows(IllegalArgumentException.class,
                () -> new Telefone("77", ""));
        assertThrows(IllegalArgumentException.class,
                () -> new Telefone("77", "39756740"));
        assertThrows(IllegalArgumentException.class,
                () -> new Telefone("77", "3975-56740"));
        assertThrows(IllegalArgumentException.class,
                () -> new Telefone("77", "numero"));
    }

    @Test
    void deveriaCriarUmTelefone() {
        assertDoesNotThrow(() -> new Telefone("77", "3975-6740"));
        assertDoesNotThrow(() -> new Telefone("77", "39755-6740"));
    }
}