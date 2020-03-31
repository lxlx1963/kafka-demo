package com.du.kafka.producer;

import com.du.kafka.config.CustomProperties;
import com.du.kafka.constant.LoggerConstant;
import com.du.kafka.util.DateUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author dxy
 */
@Component
public class DeviceProducer {
    private final static Logger kafkaErrorLogger = LoggerFactory.getLogger(LoggerConstant.KAFKA_ERROR_LOGGER);
    private final static Logger logger = LoggerFactory.getLogger(DeviceProducer.class);

    private final CustomProperties customProperties;
    private final KafkaTemplate kafkaTemplate;

    public DeviceProducer(KafkaTemplate kafkaTemplate, CustomProperties customProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.customProperties = customProperties;
    }

    /**
     * 发送数据到Kafka
     *
     * @param json String(Json格式字符串)
     */
    public void sendDataToKafka(String json) {
        // 获取主题
        String topic = customProperties.getProducerTopic();
        try {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, json);
            kafkaTemplate.send(producerRecord).get(20, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            logger.error("sendDataToKafka-----Kafka发送消息失败", e);
            kafkaErrorLogger.info(json);
        }

        System.out.println(DateUtils.getDateTime("yyyy-MM-dd HH:mm:ss.SSS") + "-----------------------------Kafka发送消息成功---------------------------");
    }
}
