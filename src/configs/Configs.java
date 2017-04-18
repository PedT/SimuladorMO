package configs;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Configs {
	
	private long mobjs, sensors, errorProbability;
	private static Configs Instanceof = null;
	
	private Configs(){
		
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader( "Configs.txt"));
			JSONObject jsonObject = (JSONObject) obj;
			mobjs = (long) jsonObject.get("MOBJS");
			sensors = (long) jsonObject.get("Sensor");
			errorProbability = (long) jsonObject.get("ChanceOfFailure");
		} 
		catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mobjs = 2;
			sensors = 3;
	
		}
	
	}
	
	public static Configs getInstance(){
		if(Instanceof == null){
			Instanceof = new Configs();
		}
		
		return(Instanceof);
	}
	
	public long getMobjs(){
		return mobjs;
	}
	
	public long getSensors(){
		return sensors;
	}
	
	public long getChance(){
		return errorProbability;
	}
	
}
