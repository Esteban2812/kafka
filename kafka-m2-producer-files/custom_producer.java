import java.util.*;
import org.apache.kafka.clients.producer.*;
public class custom_producer{
	public static void main(String[] args) throws Exception{
		
	String topicName="stud1-details";
	Properties props= new Properties();
	props.put("bootstrap.servers","localhost:9092,localhost:9093");
	props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
	
	props.put("value.serializer","custom_serializer");
	Producer<String,serializer> producer=new KafkaProducer<>(props);
	serializer stud1=new serializer (100,"ppp");
	serializer stud2=new serializer (101,"qqq");
	producer.send(new ProducerRecord<String,serializer>(topicName,"key1",stud1)).get();
	producer.send(new ProducerRecord<String,serializer>(topicName,"key2",stud2)).get();
	producer.close();
	System.out.println("Completed");
	}
}