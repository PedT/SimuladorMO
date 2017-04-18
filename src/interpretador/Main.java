package interpretador;


import socket.Servidor;
import java.io.IOException;


public class Main {
	
	public static void main(String[] args) throws IOException {
		
		Servidor server = new Servidor(12345);
		server.acceptClient();
		server.executa();
		
	}
	
	
}
