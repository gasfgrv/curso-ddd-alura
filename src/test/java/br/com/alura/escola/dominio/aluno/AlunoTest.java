package br.com.alura.escola.dominio.aluno;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AlunoTest {

    @Test
    void deveLancarExcecaoAoCadastrarTresTelefones() {
        assertThrows(MaximoDeTelefonesAtingindoException.class, () ->
                new FabricaDeAluno().comNomeCPFEmail("teste", "123.456.789-10", "teste@email.com")
                        .comTelefone("11", "1234-5678")
                        .comTelefone("11", "12345-6789")
                        .comTelefone("11", "1111-1111")
                        .criar());
    }

}