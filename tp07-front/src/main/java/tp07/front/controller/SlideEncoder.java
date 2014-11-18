package tp07.front.controller;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tp07.front.data.Slide;

public class SlideEncoder implements Encoder.Text<Slide>{
	private ObjectMapper mapper;
	
	public void destroy(){
		
	}
	
	public void init(EndpointConfig arg0){
		mapper = new ObjectMapper();
	}
	
	public String encode(Slide slide){
		try{
			return mapper.writeValueAsString(slide);
		}catch(JsonProcessingException e){
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
