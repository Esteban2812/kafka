import java.util.*;
import org.apache.kafka.clients.producer.*;
public class producer_fireforget {
  
   public static void main(String[] args) throws Exception{
           
     
      Properties kafkaProps = new Properties();
      kafkaProps.put("bootstrap.servers", "localhost:9092,localhost:9093");
      kafkaProps.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
      kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        
      Producer<String, String> producer = new KafkaProducer <>(kafkaProps);
	
	  ProducerRecord<String, String> record = new ProducerRecord<>("Employee","Name","John");			   
			try { 
				producer.send(record); } 

			catch (Exception e) { 
				e.printStackTrace();
				} 
			producer.close();

 }
}