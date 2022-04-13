package br.com.alura.escola.academico.infraestrutura.aluno;

import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.academico.dominio.aluno.CPF;
import br.com.alura.escola.academico.dominio.aluno.RepositorioDeAlunos;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeAlunosEmMemoria implements RepositorioDeAlunos {

    private final List<Aluno> matriculados = new ArrayList<>();

    @Override
    public void matricular(Aluno aluno) {
        this.matriculados.add(aluno);
    }

    @Override
    public Aluno buscarPorCpf(CPF cpf) {
        return this.matriculados.stream()
                .filter(aluno -> aluno.getCpf().equals(cpf.getNumero()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    @Override
    public List<Aluno> listarTodosAlunosCadastrados() {
        return this.matriculados;
    }
}
