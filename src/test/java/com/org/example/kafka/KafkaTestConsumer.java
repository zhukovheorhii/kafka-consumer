package com.org.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

public class KafkaTestConsumer {

    private KafkaConsumer createConsumer() {
        HashMap<String, String> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "EXTERNAL://localhost:" + KafkaTestContainer.getPort());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "intTest-${UUID.randomUUID()}");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer consumer = new KafkaConsumer(properties);
        consumer.subscribe(Collections.singleton("kafka-topic"));
        return consumer;
    }

    public ConsumerRecords consumeMessages() {
        KafkaConsumer consumer = createConsumer();
        ConsumerRecords records = consumer.poll(Duration.ofSeconds(5));
        consumer.close();
        return records;
    }

}
