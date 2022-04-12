package br.com.alura.escola.infraestrutura.indicacao;

import br.com.alura.escola.aplicacao.indicacao.EnviarEmailIndicacao;
import br.com.alura.escola.dominio.aluno.Aluno;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EnviarEmailIndicacaoComJavaMail implements EnviarEmailIndicacao {

    @Override
    public void eviarPara(Aluno indicado) {
        String smtpHostServer = "smtp.example.com";
        String emailID = "email_me@example.com";

        Properties props = System.getProperties();
        props.put("mail.smtp.host", smtpHostServer);

        Session session = Session.getInstance(props, null);
        sendEmail(session, indicado.getEmail(), "Boas vindas", "Seja bem vindo(a)!");
    }

    private void sendEmail(Session session, String email, String assunto, String mensagem) {
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
            msg.setSubject(assunto, "UTF-8");
            msg.setText(mensagem, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            System.out.println("Message is ready");
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
