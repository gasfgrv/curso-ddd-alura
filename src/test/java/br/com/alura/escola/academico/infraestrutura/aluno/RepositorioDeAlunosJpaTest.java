package br.com.alura.escola.academico.infraestrutura.aluno;

import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.shared.dominio.CPF;
import br.com.alura.escola.academico.dominio.aluno.FabricaDeAluno;
import br.com.alura.escola.academico.infraestrutura.db.DbConnectionConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RepositorioDeAlunosJpaTest {

    private static SessionFactory sessionFactory;
    private Session session;

    private RepositorioDeAlunosJpa repositorio;

    @BeforeAll
    static void beforeAll() {
        sessionFactory = DbConnectionConfig.getSessionFactory();
    }

    @AfterAll
    static void afterAll() {
        if (sessionFactory != null) sessionFactory.close();
    }

    @BeforeEach
    void setUp() {
        session = sessionFactory.openSession();
        repositorio = new RepositorioDeAlunosJpa(session);
        session.beginTransaction();
        Aluno aluno = gerarAluno("Teste", "123.456.789-10", "teste@tetse.com", "11", "1111-1111");
        repositorio.matricular(aluno);
    }

    @AfterEach
    void tearDown() {
        session.getTransaction().rollback();
        if (session != null) session.close();
    }

    @Test
    void DeveCadastrarUmAluno() {
        Aluno alunoSalvo = repositorio.buscarPorCpf(new CPF("123.456.789-10"));

        assertEquals("Teste", alunoSalvo.getNome());
        assertEquals("123.456.789-10", alunoSalvo.getCpf());
        assertEquals("teste@tetse.com", alunoSalvo.getEmail());
        assertTrue(alunoSalvo.getTelefones().stream().anyMatch(
                telefone -> "11".equals(telefone.getDdd())
                        && "1111-1111".equals(telefone.getNumero())
        ));
    }


    @Test
    void deveBuscarUmAlunoPorCPF() {
        Aluno aluno = gerarAluno("Teste2", "012.345.678-99", "teste2@tetse.com", "11", "1111-1112");
        repositorio.matricular(aluno);

        Aluno alunoSalvo = repositorio.buscarPorCpf(new CPF("012.345.678-99"));

        assertEquals("Teste2", alunoSalvo.getNome());
        assertEquals("012.345.678-99", alunoSalvo.getCpf());
        assertEquals("teste2@tetse.com", alunoSalvo.getEmail());
        assertTrue(alunoSalvo.getTelefones().stream().anyMatch(
                telefone -> "11".equals(telefone.getDdd())
                        && "1111-1112".equals(telefone.getNumero())
        ));
    }

    @Test
    void deveListarTodosOsAlunos() {
        Aluno aluno = gerarAluno("Teste2", "012.345.678-99", "teste2@tetse.com", "11", "1111-1112");
        repositorio.matricular(aluno);

        List<Aluno> alunos = repositorio.listarTodosAlunosCadastrados();

        assertEquals(2, alunos.size());
    }

    private Aluno gerarAluno(String nome, String cpf, String email, String ddd, String telefone) {
        return new FabricaDeAluno()
                .comNomeCPFEmail(nome, cpf, email)
                .comTelefone(ddd, telefone)
                .criar();
    }
}