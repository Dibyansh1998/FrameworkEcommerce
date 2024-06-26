package JsonData;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	@SuppressWarnings("deprecation")
	public List<HashMap<String,String>> getJsonDataToMap() throws IOException
	{
		//Read JSON to String
		String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//JsonData//LoginData.json"));
		
		//Convert String to HashMap to add dependency "Jackson DataBind"
		
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String,String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		
		return data;
		
		
	}
	

}
