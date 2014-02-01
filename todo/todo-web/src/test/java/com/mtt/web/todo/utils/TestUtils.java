package com.mtt.web.todo.utils;

	import java.io.IOException;
	import com.fasterxml.jackson.annotation.JsonInclude;
	import com.fasterxml.jackson.databind.ObjectMapper;


	public final  class TestUtils {


		  public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		        ObjectMapper mapper = new ObjectMapper();
		        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		        return mapper.writeValueAsBytes(object);
		    }

	}
	

