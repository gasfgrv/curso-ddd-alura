package br.com.alura.escola.aplicacao.aluno.matricula;

import br.com.alura.escola.dominio.PublicadorDeEventos;
import br.com.alura.escola.dominio.aluno.Aluno;
import br.com.alura.escola.dominio.aluno.CPF;
import br.com.alura.escola.dominio.aluno.LogDeAlunoMatriculado;
import br.com.alura.escola.infraestrutura.aluno.RepositorioDeAlunosEmMemoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatricularAlunoTest {

    @Test
    void alunoDeveriaSerPersistido() {
        RepositorioDeAlunosEmMemoria repositorio = new RepositorioDeAlunosEmMemoria();

        PublicadorDeEventos publicador = new PublicadorDeEventos();
        publicador.adicionar(new LogDeAlunoMatriculado());

        MatricularAluno useCase = new MatricularAluno(repositorio, publicador);

        MatricularAlunoDto dados = new MatricularAlunoDto("Teste", "123.456.789-10", "email@teste.com");
        useCase.executar(dados);

        Aluno encontrado = repositorio.buscarPorCpf(new CPF("123.456.789-10"));

        assertEquals("Teste", encontrado.getNome());
        assertEquals("123.456.789-10", encontrado.getCpf());
        assertEquals("email@teste.com", encontrado.getEmail());
    }

}