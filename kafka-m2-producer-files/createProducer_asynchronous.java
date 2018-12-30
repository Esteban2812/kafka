import java.util.*;
import org.apache.kafka.clients.producer.*;

public class createProducer_asynchronous {

   public static void main(String[] args) throws Exception{
      Properties props = new Properties();
      props.put("bootstrap.servers", "localhost:9092,localhost:9093");
      props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
      props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

      Producer<String, String> producer = new KafkaProducer <>(props);

      ProducerRecord<String, String> record = new ProducerRecord<>("Employee","Name","Jordan");

      producer.send(record, new DemoProducerCallback());
      System.out.println("AsynchronousProducer call completed");
      producer.close();

   }

}

    class DemoProducerCallback implements Callback{

       @Override
       public  void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null)
            System.out.println("AsynchronousProducer failed with an exception");
                else
                        System.out.println("AsynchronousProducer call Success");
       }
   }