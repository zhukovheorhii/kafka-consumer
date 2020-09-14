package com.org.example.kafka;

import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public class KafkaTestContainer extends DockerComposeContainer<KafkaTestContainer> {

    private static volatile KafkaTestContainer INSTANCE;
    private static Integer PORT;

    public static synchronized KafkaTestContainer getInstance() {
        if (INSTANCE == null) {
            return new KafkaTestContainer();
        }
        return INSTANCE;
    }

    public static synchronized Integer getPort() {
        if (PORT == null)
            PORT = getInstance().getContainerByServiceName("kafka_1").map(cont -> cont.getBoundPortNumbers().get(0)).orElse(null);
        return PORT;
    }

    //Start kafka using DOCKER-COMPOSE file
    private KafkaTestContainer() {
        super(new File("./src/test/resources/kafka-docker-compose.yml"));
        this.start();
    }
}