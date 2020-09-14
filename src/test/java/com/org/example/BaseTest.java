package com.org.example;

import com.org.example.kafka.KafkaTestConsumer;
import com.org.example.kafka.KafkaTestContainer;
import org.junit.jupiter.api.Test;
import spock.lang.Shared;

public class BaseTest {
    @Shared
    final private KafkaTestContainer kafka = KafkaTestContainer.getInstance();


    @Test
    void simpleTest() {
        KafkaTestConsumer consumer = new KafkaTestConsumer();
        consumer.consumeMessages();
    }
}
