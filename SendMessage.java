/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pablo_root.v1.gmail_sender;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author pablo
 */
public class SendMessage {
    private static String remetenteGmail;
    private static String password;
    private String assunto;
    private int quantidade;
    private String mensagen;
    
    
    public SendMessage(String _gmail , String senha , String _assunto , String _mensagen) throws AddressException {
        this.assunto = _assunto;
        this.mensagen = _mensagen;
        SendMessage.remetenteGmail = _gmail;
        SendMessage.password = senha;
        // Parâmetros de conexão com servidor Gmail
        Properties props = new Properties();
        props.put("mail.smtp.host" , "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port" , "465");
        props.put("mail.smtp.socketFactory.class" , "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth" , "true");
        props.put("mail.smtp.port" , "465");
        
        
        Session sessao = Session.getDefaultInstance(props , new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SendMessage.remetenteGmail, SendMessage.password);
            }
        
        });
        
        sessao.setDebug(true);
        int c = 0 , i = 1;
        while (c < Gmail.gmails.length) {
            
        
            try {
            
                Message mensagem = new MimeMessage(sessao);
                mensagem.setFrom(new InternetAddress(SendMessage.remetenteGmail));
            
                Address[] toUser = InternetAddress.parse(Gmail.gmails[c] + "," + Gmail.gmails[i]);
            
                mensagem.setRecipients(Message.RecipientType.TO, toUser);
                mensagem.setSubject(assunto);
                mensagem.setText(mensagen);
            
                Transport.send(mensagem);
            
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            c += 2;
        }
        System.out.println("> Mensagem enviada com êxito para todos o gmails");
        
    }   
}
