package socket;

import java.io.InputStream;
import java.util.Scanner;

import interpretador.Interpretador;

public class InputHandler implements Runnable {
	private InputStream input;
	private Interpretador interpretador;
	
	public InputHandler(InputStream input){
		this.input = input;
		
	}
	
	public void run(){
		Scanner s = new Scanner(this.input);
		
		while(s.hasNextLine()){
			//System.out.println(s.nextLine());
			
			
		}
		
		s.close();
	}
	
}
