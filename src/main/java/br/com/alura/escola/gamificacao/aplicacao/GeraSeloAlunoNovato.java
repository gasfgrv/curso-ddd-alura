package br.com.alura.escola.gamificacao.aplicacao;

import br.com.alura.escola.gamificacao.dominio.selo.RepositorioDeSelos;
import br.com.alura.escola.gamificacao.dominio.selo.Selo;
import br.com.alura.escola.shared.dominio.CPF;
import br.com.alura.escola.shared.dominio.evento.Evento;
import br.com.alura.escola.shared.dominio.evento.Ouvinte;
import br.com.alura.escola.shared.dominio.evento.TipoDeEvento;

public class GeraSeloAlunoNovato extends Ouvinte {

    private final RepositorioDeSelos repositorio;

    public GeraSeloAlunoNovato(RepositorioDeSelos repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    protected void reage(Evento evento) {
        CPF cpf = (CPF) evento.informacoes().get("cpf");
        Selo novato = new Selo(cpf, "Novato");
        repositorio.adicionar(novato);
    }

    @Override
    protected boolean deveProcessar(Evento evento) {
        return evento.tipo() == TipoDeEvento.ALUNO_MATRICULADO;
    }
}
