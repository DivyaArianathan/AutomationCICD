package Perficient.DataReader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class DataReader {
	
	public List<HashMap<String, String>> getJsonData() throws IOException
	{		
		//Json file content to String
		String JsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//Perficient//DataReader//PurchaseData.json"), StandardCharsets.UTF_8);
		
		//String to Hashmap
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
	}

}
