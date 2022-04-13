package br.com.alura.escola.gamificacao.dominio.selo;

import br.com.alura.escola.shared.dominio.CPF;

import java.util.List;

public interface RepositorioDeSelos {

    void adicionar(Selo selo);

    List<Selo> selosDoAlunoPorCPF(CPF cpf);

}
