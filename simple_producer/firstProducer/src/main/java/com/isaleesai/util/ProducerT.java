package com.isaleesai.util;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isaleesai.entidades.Producto;

public  class ProducerT {

		public static void processrows(Producto[] productos,Producer<String,String> producer,String topicName) throws JsonProcessingException,InterruptedException, ExecutionException {
			
			for(int i=0;i<productos.length;i++) {
				Producto producto = productos[i];
				if(producto!=null) {
					String jsonProducto = (new ConvertidorJson()).convertirAJsonStr(producto);
					producer.send(new ProducerRecord<String,String>(topicName,"key"+String.valueOf(i),jsonProducto)).get();
					//System.out.println("producing record " + String.valueOf(i));
				}
			}
			System.out.println("Products processed#="+String.valueOf(productos.length));
			
		}
}
