package com.isaleesai.util;

import java.util.Properties;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.isaleesai.entities.Producto;

import com.isaleesai.entidades.Producto;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ConvertidorJson {
	private Properties kafkaProps = new Properties();
	
	
	public String convertirAJson() throws JsonGenerationException, JsonMappingException, IOException {
	
	String jsonFile="C:\\Users\\Esteban\\Documents\\file.json";
	
	ObjectMapper mapper = new ObjectMapper();
	//Producto producto = new Producto();
	Producto producto = new Producto(10.0,"Traduccion frances/italiano", jsonFile, jsonFile, jsonFile, jsonFile, jsonFile, 0, 0, jsonFile, jsonFile, 0, jsonFile);
	
	mapper.writeValue(new File(jsonFile), producto);
	
	return jsonFile;
	
	}
	
	public String convertirAJsonStr(Producto producto) throws JsonProcessingException  {
		
		//String jsonFile="C:\\Users\\Esteban\\Documents\\file.json";
		
		ObjectMapper mapper = new ObjectMapper();
		
		//Producto producto = new Producto(10,"Traduccion frances/italiano");
		
		String contenidoJson = mapper.writeValueAsString( producto);
		
		return contenidoJson;
		
		}
	
	public Producto convertirDeArchivoAObjeto() throws JsonParseException, JsonMappingException, IOException {
		String jsonFile="C:\\Users\\Esteban\\Documents\\file-copy.json";
		ObjectMapper mapper = new ObjectMapper();
		Producto oProducto = mapper.readValue(new File(jsonFile), Producto.class);
		return oProducto;
		
	}
	
}
