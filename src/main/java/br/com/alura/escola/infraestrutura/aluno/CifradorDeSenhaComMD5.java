package br.com.alura.escola.infraestrutura.aluno;

import br.com.alura.escola.dominio.aluno.CifradorDeSenha;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CifradorDeSenhaComMD5 implements CifradorDeSenha {

    @Override
    public String cifrarSenha(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(senha.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("erro ao gerar o hash");
        }
    }

    @Override
    public boolean validarSenhaCifrada(String senhaCifrada, String senha) {
        return senhaCifrada.equals(cifrarSenha(senha));
    }

}
