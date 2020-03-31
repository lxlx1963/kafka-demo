package com.du.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dxy
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {
    @Value("${kafka.consumer.bootstrap.servers}")
    private String servers;

    @Value("${kafka.consumer.session.timout.ms}")
    private String sessionTimeout;

    @Value("${kafka.consumer.request.timeout.ms}")
    private String requestTimeout;

    @Value("${kafka.consumer.max.poll.interval.ms}")
    private String pollInterval;

    @Value("${kafka.consumer.max.poll.records}")
    private String pollRecords;

    @Value("${kafka.consumer.heartbeat.interval.ms}")
    private String heartbeatInterval;

    @Value("${kafka.consumer.auto.commit.interval.ms}")
    private String commitInterval;


    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(1);
        factory.getContainerProperties().setPollTimeout(30000);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeout);
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, pollInterval);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, pollRecords);
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, heartbeatInterval);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, commitInterval);
        return props;
    }
}
