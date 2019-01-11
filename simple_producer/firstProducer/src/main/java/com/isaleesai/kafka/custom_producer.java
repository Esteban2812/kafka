package com.isaleesai.kafka;

import java.util.*;
import java.util.concurrent.ExecutionException;

import javax.print.attribute.standard.DateTimeAtCompleted;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.apache.kafka.clients.producer.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isaleesai.entidades.Producto;
import com.isaleesai.util.ConvertidorJson;
import com.isaleesai.util.ProducerT;

public class custom_producer{
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		
	String topicName="productos3";
	Properties props= new Properties();
	props.put("bootstrap.servers","localhost:9092");
	props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");	
	props.put("value.serializer","com.isaleesai.kafka.custom_serializer");
	Producer<String,serializer> producer=new KafkaProducer<>(props);
	serializer stud1=new serializer (100,"Aidan");
	serializer stud2=new serializer (101,"Leonardo");
	producer.send(new ProducerRecord<String,serializer>(topicName,"key1",stud1)).get();
	producer.send(new ProducerRecord<String,serializer>(topicName,"key2",stud2)).get();
	producer.close();


	System.out.println("Completed");
	Properties props2= new Properties();
	props2.put("bootstrap.servers","localhost:9092");
	props2.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");	
	props2.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
	Producer<String,String> producerJson=new KafkaProducer<>(props2);
	readFile2("/home/cambiante/kafka/m2-producers/products.csv",producerJson,topicName);
	//producerJson.close();
	System.out.println("Finish");

	}

	public static void readFile(String path_file_name,Producer<String,String> producer,String topicName) throws IOException{
		///home/cambiante/kafka/m2-producers/products.csv
		BufferedReader br = null;
		try{
			File file = new File(path_file_name);

			br = new BufferedReader(new FileReader(file));
			String line;
			int i=0;
			Instant t1 = Instant.now();
			//Date start = new Date();
			while((line=br.readLine()) != null){
				//System.out.println(i);
				if(i > 0){
					//System.out.println(line);
					String[] fields=line.split(",");
					Producto producto = new Producto(
													Double.parseDouble(fields[0]),
													fields[1],
													fields[2],
													fields[3],
													fields[4],
													fields[5],
													fields[6],
													Double.parseDouble(fields[7]),
													Integer.parseInt(fields[8]),
													fields[9],
													fields[10],
													Double.parseDouble(fields[11]),
													fields[12]													
													);
				//System.out.println("Product Description at " + String.valueOf(i) +":"+producto.toString());
				//System.out.println("Product Description at " + String.valueOf(i)+":"+producto.getDescription());
				String jsonProducto = (new ConvertidorJson()).convertirAJsonStr(producto);
				producer.send(new ProducerRecord<String,String>(topicName,"key"+String.valueOf(i),jsonProducto)).get();
				}
				i+=1;
				if(i==100000)
					break;
				}
				System.out.println(i%100 );
				if(i%100 == 0) {
					System.out.println("Processing "+String.valueOf(i) + " rows.");
				}
			//System.out.println("seconds="+String.valueOf((Duration.between(t1, Instant.now()).getSeconds())));
			//System.out.println("ms="+String.valueOf((Duration.between(t1, Instant.now()).getNano()/1000000)));
			long seconds = ChronoUnit.SECONDS.between(t1,Instant.now());
	        long milis = ChronoUnit.MILLIS.between(t1,Instant.now());
	        
	        System.out.println("Seconds="+String.valueOf(seconds));
	        System.out.println("Milis="+String.valueOf(milis));
	        
			br.close();
		}catch(Exception ex){			
			System.out.println("Error is:" + ex.getMessage());
		}finally {
			if(br != null) {
				br.close();
			}
			producer.close();
		}
	}

	
	public static void readFile2(String path_file_name,Producer<String,String> producer,String topicName) throws IOException{
		
		BufferedReader br = null;
		try{
			File file = new File(path_file_name);

			br = new BufferedReader(new FileReader(file));
			String line;
			int i=0;
			Instant t1 = Instant.now();
			//Date start = new Date();
			int constant = 1500;
			Producto[] productos = new Producto[constant] ;
			Producto[] productos2 = new Producto[constant] ;
			
			int j=0;
			System.out.println("Starting while");
			while((line=br.readLine()) != null){
				//System.out.println("i="+String.valueOf(i));
				if(i > 0){
					//System.out.println(line);
					String[] fields=line.split(",");
					Producto producto = new Producto(
													Double.parseDouble(fields[0]),
													fields[1],
													fields[2],
													fields[3],
													fields[4],
													fields[5],
													fields[6],
													Double.parseDouble(fields[7]),
													Integer.parseInt(fields[8]),
													fields[9],
													fields[10],
													Double.parseDouble(fields[11]),
													fields[12]													
													);
				if(j>=0  && j<constant) {
					productos[i%constant]=producto;
					j+=1;
				};
				if(j>=constant  && j<constant*2) {
					productos2[i%constant]=producto;
					j+=1;
				};
				//System.out.println("j="+String.valueOf(j)+", "+String.valueOf(constant*2));
				if(j==constant*2)
				{
					final ProducerT pc = new ProducerT(); 
					Thread th1 = new Thread(new Runnable() 
			        { 
			            @Override
			            public void run() 
			            { 
			                try
			                { 
			                    pc.processrows(productos, producer, topicName); 
			                } 
			                catch(InterruptedException | JsonProcessingException | ExecutionException e) 
			                { 
			                    e.printStackTrace(); 
			                } 
			            } 
			        }); 
					Thread th2 = new Thread(new Runnable() 
			        { 
			            @Override
			            public void run() 
			            { 
			                try
			                { 
			                    pc.processrows(productos2, producer, topicName); 
			                } 
			                catch(InterruptedException | JsonProcessingException | ExecutionException e) 
			                { 
			                    e.printStackTrace(); 
			                } 
			            } 
			        });
					

			        // Start both threads
					System.out.println("Starting threads");
			        th1.start(); 
			        th2.start(); 
			        System.out.println("threads joining");
			        // t1 finishes before t2 
			        th1.join(); 
			        th2.join();
			        System.out.println("threads finished");
			        //reset
			        j=0;
				}
				i=i+1;
				if(i==10000)
					break;
				
				//System.out.println(i%100 );
				if(i%100 == 0) {
					System.out.println("Processing "+String.valueOf(i) + " rows.");
				}
				}else {
					i+=1;
				}
			}
				//System.out.println("seconds="+String.valueOf((Duration.between(t1, Instant.now()).getSeconds())));
			//System.out.println("ms="+String.valueOf((Duration.between(t1, Instant.now()).getNano()/1000000)));
			long seconds = ChronoUnit.SECONDS.between(t1,Instant.now());
	        long milis = ChronoUnit.MILLIS.between(t1,Instant.now());
	        
	        System.out.println("Seconds="+String.valueOf(seconds));
	        System.out.println("Milis="+String.valueOf(milis));
	        
			br.close();
			System.out.println("End");
		}catch(Exception ex){			
			System.out.println("Error is:" + ex.getMessage());
		}finally {
			if(br != null) {
				br.close();
			}
			producer.close();
			System.out.println("Producer closed");
		}
	}
}