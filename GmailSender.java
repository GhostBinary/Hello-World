/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pablo_root.v1.gmail_sender;

import java.util.InputMismatchException;
import java.util.Scanner;
import javax.mail.internet.AddressException;

class Gmail {
    protected static boolean sucesso = true; 
    protected static String[] gmails;
    private String password;
    private String assunto;
    private String mensagem;
    private String[] nomes;
    
    public Gmail(int qntGmails , int qntNomes) {
        this.gmails = new String[qntGmails];
        this.nomes = new String[qntNomes];
    }
    
    // getters
    
    
    protected String getAssunto() {
        return this.assunto;
    }
    
    protected String getMensagem() {
        return this.mensagem;
    }
    
    protected String getGmail(int posicao1) {
        return this.gmails[posicao1];
    }
    
    protected String getNome(int posicao2) {
        return this.nomes[posicao2];
    }
    
    protected String getPassword() {
        return this.password;
    }
    
    // setters
    
    protected void setAssunto(String _assunto) {
        this.assunto = _assunto;
    }
    
    protected void setMensagem(String msg) {
        this.mensagem = msg;
    }
    
    protected void setPassword(String _password) {
        this.password = _password;
    }
    
    protected void setGmail(String gmail , int posicao3) {
        String gmailAux = gmail;
        if (verificaEmail(gmailAux)) {
            this.gmails[posicao3] = gmailAux;
        } else {
            this.gmails[posicao3] = null;
        }
        
    }
    
    protected void setNome (String _nome , int posicao4) {
        this.nomes[posicao4] = _nome; 
    }
    
    public boolean verificaEmail(String email) {
        String letra;
        for (int i = 0; i < email.length(); i++) {
            letra = Character.toString(email.charAt(i));
            if (letra.equals("@")) {
                
                letra = email.substring(i, email.length());
                
                if (letra.equals("@gmail.com")) {
                    this.sucesso = true;
                    return true;
                }
            } 
        }
        this.sucesso = false;
        return false;
    }
}

class InicializarVars extends Gmail {
    
    public InicializarVars(int qntEmails , int qntNames) {
        super(qntEmails , qntNames);
        
    }
    
}

class Enviar extends SendMessage {
    public Enviar(String _gmail , String senha , String assunto , String mensagen) throws AddressException {
        super(_gmail , senha , assunto , mensagen);
        
    }     
}
    

/**
 *
 * @author pablo
 */
public class GmailSender {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AddressException {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("|------------------------------|");
        System.out.println("|---Gmail Sender v1 By Reloog--|");
        System.out.println("|------------------------------|");
        
        int qnt = 0;
        try {
            System.out.println("Digite a quantidade de gmails a cadastrar:");
            System.out.print("> ");
            qnt = teclado.nextInt();
        } catch(InputMismatchException e) {
            System.out.println("> Formato de número desconhecido");
            System.exit(1);
        }
        
        InicializarVars gmail = new InicializarVars(qnt , qnt);
        String conta , nome;
        int auxContador = 1;
        for (int i = 0; i < qnt; i++) {
            System.out.println(" ");
            System.out.print(auxContador + " email> ");
            conta = teclado.next();
            gmail.setGmail(conta , i);
            
            
            if (Gmail.sucesso == false) {
                System.out.println("> Email ínvalido ");
                gmail.setNome(null , i);
                auxContador++;
                continue;
            }
            
            System.out.print(auxContador + " nome> ");
            nome = teclado.next();
            gmail.setNome(nome, i);
            
            auxContador++;
        }
        
        
        System.out.println(" ");
        System.out.println("> E-mails cadastrados OBS: E-mails invalidos são excluidos");
        System.out.println(" ");
        for (int c = 0; c < qnt; c++) {
            
            if (gmail.getGmail(c) != null) {
                
                System.out.println(gmail.getGmail(c) + "    \t" + "    \t" +  gmail.getNome(c));
            }
        }
        // pegando imformações
        System.out.println(" ");
        System.out.println(" ");
        System.out.print("digite seu gmail>");
        String myemail = teclado.next();
        System.out.println(" ");
        System.out.print("digite sua senha>");
        String senha = teclado.next();
        gmail.setPassword(senha);
        System.out.println(" ");
        System.out.print("digite o assunto>");
        String assunto = teclado.next();
        gmail.setAssunto(assunto);
        System.out.println(" ");
        System.out.print("digite a mensagen>");
        String msg = teclado.next();
        gmail.setMensagem(msg);
        // fim 
        
        new Enviar(myemail , gmail.getPassword() , gmail.getAssunto() , gmail.getMensagem());
        
        msg = null; 
        assunto = null;
        senha = null;
        conta = null;
        nome = null;
        
        
    }
    
    
}
