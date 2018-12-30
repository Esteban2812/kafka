import java.util.*;
import org.apache.kafka.clients.producer.*;
public class createProducer_synchronous {
  
   public static void main(String[] args) throws Exception{
           
     
      Properties kafkaProps = new Properties();
      kafkaProps.put("bootstrap.servers", "localhost:9092,localhost:9093");
      kafkaProps.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");         
      kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	        
      Producer<String, String> producer = new KafkaProducer <>(kafkaProps);
	
	  ProducerRecord<String, String> record = new ProducerRecord<>("Employee","Name","James");
	  try { 
			producer.send(record).get();
		 } 

		catch (Exception e) { 
			e.printStackTrace(); 
		 } 

	  producer.close();
	  
	  System.out.println("SimpleProducer Completed.");
   }
}