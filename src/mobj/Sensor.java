package mobj;

import java.util.concurrent.ThreadLocalRandom;

public class Sensor {

	private String nome;
	private double value_d, Drange_low, Drange_up;

	
	public Sensor(double intervalo_low, double intervalo_up, float taxa_emissao, String name){
		this.nome = name;
		this.Drange_low = intervalo_low;
		this.Drange_up = intervalo_up;
	}
	
	public String readName(){
		return(this.nome);
	}
	
	
	public Double readValue(){	
			return(this.value_d);
		
	}
	
	public void writeValue(){
		this.value_d = ThreadLocalRandom.current().nextDouble(this.Drange_low, this.Drange_up + 1);
		
	}
	
	public void writeValue(Double mensagem){
		this.value_d = mensagem;
		
	}
	
}
