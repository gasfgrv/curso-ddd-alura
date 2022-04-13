package br.com.alura.escola.academico;

import br.com.alura.escola.academico.aplicacao.aluno.matricula.MatricularAluno;
import br.com.alura.escola.academico.aplicacao.aluno.matricula.MatricularAlunoDto;
import br.com.alura.escola.academico.dominio.PublicadorDeEventos;
import br.com.alura.escola.academico.dominio.aluno.LogDeAlunoMatriculado;
import br.com.alura.escola.academico.dominio.aluno.RepositorioDeAlunos;
import br.com.alura.escola.academico.infraestrutura.aluno.RepositorioDeAlunosJpa;
import br.com.alura.escola.academico.infraestrutura.db.DbConnectionConfig;
import org.hibernate.Session;

public class MatricularAlunoPorLinhaDeComando {

    public static void main(String[] args) {
        String nome = args[0];
        String cpf = args[1];
        String email = args[2];

        Session session = DbConnectionConfig.getSessionFactory().openSession();
        RepositorioDeAlunos repositorioDeAlunos = new RepositorioDeAlunosJpa(session);

        PublicadorDeEventos publicador = new PublicadorDeEventos();
        publicador.adicionar(new LogDeAlunoMatriculado());

        MatricularAluno matricularAluno = new MatricularAluno(repositorioDeAlunos, publicador);
        matricularAluno.executar(new MatricularAlunoDto(nome, cpf, email));
    }

}
