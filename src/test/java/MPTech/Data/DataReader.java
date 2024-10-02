package MPTech.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
	
		//first convert json into a String
		String jsonContent = FileUtils.readFileToString(new File(filepath),

				StandardCharsets.UTF_8); //StandardCharsets.UTF_8 is the encoding format to write the string
	
		//Now String into HashMap using Jackson databind from MVN repository
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
});
	
		return data;
	
		//in above we are creating object for the class "ObjectMapper" in that we are calling the class "readValue" in which there are 2 things expected 
		//1st is which file we want to read
		//2nd is in what type we want to convert that file, in that we want to be in List , We have 2 data set so we are giving <String , String>
	}
}
