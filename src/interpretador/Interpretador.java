package interpretador;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import configs.Configs;
import mobj.MOBJ;

public class Interpretador {

	private List<MOBJ> Lmobj;
	private PrintStream saida;
	private Configs conf;
	
	public Interpretador(PrintStream saida){
		 this.Lmobj = new ArrayList<MOBJ>();
		 this.saida = saida;
		 
		 this.conf = Configs.getInstance();
		
		 int nSensor = (int) conf.getInstance().getSensors();
		 
		 for(int i = 0; i < (int) conf.getInstance().getMobjs(); i++){
			 this.Lmobj.add(new MOBJ());
			 this.Lmobj.get(i).AdicionaNSensores(nSensor, 0, 100);
			 this.Lmobj.get(i).setRandomValuesALL();
		 }
		 
	}
	
	public void handle(String mensagem) throws ParseException{
		
		
		
			System.out.println(mensagem);
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(mensagem);
        
         
			String command = (String) jsonObject.get("Nome");
         
			if(command.equals("StartScan")){
				//Faz o scan de todos os elementos
				System.out.println("Recebi o StartScan!");
							
				 for(int i = 0; i < (int) conf.getInstance().getMobjs(); i++){					
					 this.Lmobj.get(i).setRandomValuesALL();
				 }
				
				JSONObject jason = new JSONObject();
				JSONArray listMOBJ = new JSONArray();
				JSONArray listSensor = new JSONArray();
				JSONObject Intermediario = new JSONObject();
			
				for(int i = 0; i < this.Lmobj.size(); i++){
					
					for(int j = 0; j < this.Lmobj.get(i).totalSensor(); j++)
					{
						listSensor.add(this.Lmobj.get(i).getSensorValue(j));
					}
					Intermediario.put("Sensors", listSensor.clone());
					Intermediario.put("Nome", this.Lmobj.get(i).getName());
					
					Random r = new Random(); 
					int x = r.nextInt(100);
									
					if(x >= this.conf.getChance())
					{
						Intermediario.put("ConnectStatus", "Connected");

					}
					else
					{
						Intermediario.put("ConnectStatus", "Disconnected");
						System.out.println("Desconectei o " + Lmobj.get(i).getName());
					}					
					listMOBJ.add(Intermediario.clone());
					listSensor.clear();
					Intermediario.clear();
				}
			
				jason.put("Objetos", listMOBJ);
				jason.put("Comando", "StartScanReply");
				
				saida.println(jason.toString());
				
			}	
			else if(command.equals("Initialize")){
				
				System.out.println("Recebi o Initialize!");
				
				JSONObject jason = new JSONObject();
				JSONArray listMOBJ = new JSONArray();			
				JSONObject Intermediario = new JSONObject();
			
				for(int i = 0; i < this.Lmobj.size(); i++){				
					Intermediario.put("Nome", this.Lmobj.get(i).getName());					
					listMOBJ.add(Intermediario.clone());
					Intermediario.clear();
				}
			
				jason.put("Objetos", listMOBJ);
				jason.put("Comando", "InitializeReply");
				
				saida.println(jason.toString());
			}
			else if(command.equals("Destroy")){
				System.out.println("Recebi o Destroy!");
				this.Lmobj.clear();
			}
			else if(command.equals("WriteSensor")){
				System.out.println("Recebi o Write!");
				
				String endereco = (String) jsonObject.get("macAdd");
				String temp = (String) jsonObject.get("sensorName");				
				Double temp2 = Double.parseDouble(temp);	
				int sensor = temp2.intValue();	
				Double valor = (Double) jsonObject.get("sensorValue");
		
				
				for(int k = 0; k < Lmobj.size(); k ++){
					if(Lmobj.get(k).getName().equals(endereco)){
						Lmobj.get(k).setSensorValue(sensor, valor);
					}
				}

			}
			
	}
	
}
