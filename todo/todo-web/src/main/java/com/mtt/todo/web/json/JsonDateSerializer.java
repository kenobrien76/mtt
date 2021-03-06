package com.mtt.todo.web.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;
@Component
public class JsonDateSerializer extends JsonSerializer<Date	> {
	
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM' 'HH:mm:ss");
	

	@Override
	public void serialize(Date date, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		 String formattedDate = dateFormat.format(date);
		 
		 jgen.writeString(formattedDate);
		
	}

}
