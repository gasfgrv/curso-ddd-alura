package br.com.alura.escola.infraestrutura.aluno;

import br.com.alura.escola.dominio.aluno.Aluno;
import br.com.alura.escola.dominio.aluno.CPF;
import br.com.alura.escola.dominio.aluno.RepositorioDeAlunos;
import org.hibernate.Session;

import java.util.List;

public class RepositorioDeAlunosJpa implements RepositorioDeAlunos {

    private final Session session;

    public RepositorioDeAlunosJpa(Session session) {
        this.session = session;
    }

    @Override
    public void matricular(Aluno aluno) {
        this.session.persist(aluno);
    }

    @Override
    public Aluno buscarPorCpf(CPF cpf) {
        return this.session.find(Aluno.class, cpf);
    }

    @Override
    public List<Aluno> listarTodosAlunosCadastrados() {
        return this.session.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
    }
}
