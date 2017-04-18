package mobj;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class MOBJ {

	private String name;
	private int count;
		
	public List<Sensor> ListaSensivel;	

	public MOBJ(){
		this.name = UUID.randomUUID().toString();
		this.ListaSensivel = new ArrayList<Sensor>();
	}
	
	public String getName(){
		return this.name;
	}
	
	public void AdicionaNSensores(int i, double intervalo_l, double intervalo_u){
		int j;
		for(j=0; j<i; j++){
			Sensor sensor = new Sensor( intervalo_l,  intervalo_u, 2, "S" + Integer.toString(this.count));
			this.count+=1;
			ListaSensivel.add(sensor);
		}
	}
	
	public Double getSensorValue(int index){
		if(this.ListaSensivel.size() > 0){
			return(this.ListaSensivel.get(index).readValue());
		}
		return(0.0);
	}
	
	public void setRandomValuesALL(){
		int l = this.ListaSensivel.size();
		if(l>0){
			int i;
			for(i = 0; i < l; i++){
				this.ListaSensivel.get(i).writeValue();
			}
		}
	}
	
	public void setSensorValue(int index, Double mensagem){
		if(this.ListaSensivel.size() > 0){
			this.ListaSensivel.get(index).writeValue(mensagem);
		}
	}
	
	public void setSensorValue(int index){
		if(this.ListaSensivel.size() > 0){
			this.ListaSensivel.get(index).writeValue();
		}
	}
	
	
	public int totalSensor(){
		return this.ListaSensivel.size();
	}
	
	

}
