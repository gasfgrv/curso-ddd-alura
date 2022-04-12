package br.com.alura.escola.infraestrutura.aluno;

import br.com.alura.escola.dominio.aluno.Aluno;
import br.com.alura.escola.dominio.aluno.CPF;
import br.com.alura.escola.dominio.aluno.Email;
import br.com.alura.escola.dominio.aluno.FabricaDeAluno;
import br.com.alura.escola.dominio.aluno.RepositorioDeAlunos;
import br.com.alura.escola.dominio.aluno.Telefone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeAlunosJdbc implements RepositorioDeAlunos {

    private final Connection connection;

    public RepositorioDeAlunosJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void matricular(Aluno aluno) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO ALUNO VALUES (?, ?, ?)")) {
            ps.setString(1, aluno.getCpf());
            ps.setString(1, aluno.getNome());
            ps.setString(1, aluno.getEmail());
            ps.execute();

            aluno.getTelefones().forEach(this::insereTelefone);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void insereTelefone(Telefone telefone) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO TELEFONE VALUES (?, ?)")) {
            ps.setString(1, telefone.getDdd());
            ps.setString(1, telefone.getNumero());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Aluno buscarPorCpf(CPF cpf) {
        Aluno aluno = null;

        try (PreparedStatement ps = connection.prepareStatement("SELECT CPF, NOME, EMAIL FROM ALUNO WHERE CPF = ?");
             ResultSet rs = ps.executeQuery()) {

            ps.setString(1, cpf.getNumero());

            boolean encontrado = rs.next();
            if (!encontrado) {
                throw new RuntimeException("Aluno não encontrado");
            }

            String nome = rs.getString("NOME");
            Email email = new Email(rs.getString("EMAIL"));

            Aluno alunoEncontrado = new Aluno(cpf, nome, email);
            adicionarTelefonesAoAluno(alunoEncontrado);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return aluno;
    }

    private void adicionarTelefonesAoAluno(Aluno aluno) {
        List<Telefone> telefones = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement("SELECT DDD, NUMERO FROM ALUNO WHERE CPF_ALUNO = ?");
             ResultSet rs = ps.executeQuery()) {

            ps.setString(1, aluno.getEmail());

            while (rs.next()) {
                String ddd = rs.getString("DDD");
                String numero = rs.getString("NUMERO");

                telefones.add(new Telefone(ddd, numero));
            }

            telefones.forEach(telefone -> aluno.adicionarTelefone(telefone.getDdd(), telefone.getNumero()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Aluno> listarTodosAlunosCadastrados() {
        List<Aluno> alunos = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement("SELECT CPF, NOME, EMAIL FROM ALUNO");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String cpf = rs.getString("CPF");
                String nome = rs.getString("NOME");
                String email = rs.getString("EMAIL");

                Aluno aluno = new FabricaDeAluno()
                        .comNomeCPFEmail(cpf, nome, email)
                        .criar();

                alunos.add(aluno);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alunos;
    }

}
