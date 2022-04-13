package br.com.alura.escola.academico.dominio;

public abstract class Ouvinte {

    public void processa(Evento evento) {
        if (this.deveProcessar(evento)) {
            this.reage(evento);
        }
    }

    protected abstract void reage(Evento evento);

    protected abstract boolean deveProcessar(Evento evento);

}
