package br.com.alura.escola.academico.dominio.aluno;

import br.com.alura.escola.shared.dominio.CPF;

import java.util.List;

public interface RepositorioDeAlunos {

    void matricular(Aluno aluno);

    Aluno buscarPorCpf(CPF cpf);

    List<Aluno> listarTodosAlunosCadastrados();

}
