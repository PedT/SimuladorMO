package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import interpretador.Interpretador;

import java.io.PrintStream;


public class Servidor {

	private ServerSocket servidor;
	private Socket cliente;
	private Interpretador interpretador;
	private PrintStream saida;
	private Scanner entrada;
	
	public Servidor (int porta) throws IOException {
      
	     servidor = new ServerSocket(porta); 
	     System.out.println("Porta " + porta + " aberta!");

	     
	   }
	
	 public void acceptClient() throws IOException {
		   this.cliente = servidor.accept();
		   System.out.println("Nova conexão com o cliente " +   cliente.getInetAddress().getHostAddress());	  
		   this.entrada = new Scanner(cliente.getInputStream());
		   this.saida = new PrintStream(cliente.getOutputStream());	   
		   this.interpretador = new Interpretador(this.saida);
		   
	   }
	 
	 public void executa(){
		 if(this.interpretador != null){
			 while(entrada.hasNextLine())
			 {			 
				 try {
						interpretador.handle(this.entrada.nextLine());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
		 }
	 }
	
}
