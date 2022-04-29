package com.velmdart.sim;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Component
public class MqttServerSim {
	public int count = 1;
	public String Simulate(String mac, Integer cyct, Integer injt) throws FileNotFoundException {
		String payl = "";
		payl = jsonarrToString(MakePayload(mac, cyct, injt));
		return payl;
	}
	
	
	public JSONArray MakePayload(String mac, Integer cycleTime, Integer injectTime) {
		JSONArray payload = new JSONArray();
		int vi = 1;
		long epoch = System.currentTimeMillis()*1000; //micro seconds
		for(int i = 0; i < 7; i++) {
			JSONObject pkt  = new JSONObject();
			pkt.put("machineId", mac);
			pkt.put("count", count++);
			pkt.put("validInjection", vi);
			pkt.put("cycleStartTime", epoch);
			pkt.put("cycleTime", cycleTime * 1000000);
			pkt.put("injectionTime", injectTime * 1000000);
			epoch = epoch + (cycleTime*1000000);
			payload.put(pkt);
		}
		return payload;
	}
	
	public String jsonarrToString(JSONArray arr) {
		List<String> arrlist = new ArrayList<String>();
		for(int i=0; i< arr.length(); i++){
		    arrlist.add(
		    		"{\"count\":" + arr.getJSONObject(i).getInt("count") +
		    		", \"cycleTime\":" +arr.getJSONObject(i).getLong("cycleTime")+
		    		", \"machineId\":" + "\"" + arr.getJSONObject(i).getString("machineId")+"\"" +
		    		", \"validInjection\":" + arr.getJSONObject(i).getInt("validInjection")+
		    		", \"cycleStartTime\":" + arr.getJSONObject(i).getLong("cycleStartTime") +
		    		", \"injectionTime\":" + arr.getJSONObject(i).getInt("injectionTime") +"}");
		}
		StringBuilder sb = new StringBuilder();
		for(String s : arrlist) {
			sb.append(",").append(s);
		}
		return "[" + sb.deleteCharAt(0).toString() + "]";
	}
	

}
