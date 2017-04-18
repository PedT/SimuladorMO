package socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

import interpretador.Interpretador;

public class Cliente {
	private Socket cliente;
	private Scanner entrada;
	//private PrintStream saida;
	private Interpretador interpretador;
	
	public Cliente(int porta) throws UnknownHostException, IOException{
		this.cliente = new Socket("139.82.100.66", porta);

	     System.out.println("O cliente se conectou ao servidor!");
	     //teclado = new Scanner(System.in);
	     //this.saida = new PrintStream(cliente.getOutputStream());
	     this.interpretador = new Interpretador(new PrintStream(cliente.getOutputStream()));
	     this.entrada = new Scanner(this.cliente.getInputStream());
	    
	}

	public void executa() throws IOException{
		
		while(true){
			//System.out.println(s.nextLine());
			try {
				interpretador.handle(entrada.nextLine());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		/*InputHandler inp = new InputHandler(cliente.getInputStream());
		new Thread(inp).start();
		while (teclado.hasNextLine()) {
			saida.println(teclado.nextLine());
		      
		     }*/
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException{
		Cliente clientezin = new Cliente(12345);
		clientezin.executa();
		
		
	}
}
