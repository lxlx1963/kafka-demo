package com.polarico.kafka.producer;

import com.polarico.kafka.aop.ControllerAop;
import com.polarico.kafka.config.CustomProperties;
import com.polarico.kafka.constant.LoggerConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

/**
 * @author dxy
 */
@Component
public class DeviceProducer {
    private final static Logger kafkaErrorLogger = LoggerFactory.getLogger(LoggerConstant.KAFKA_ERROR_LOGGER);
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
        String topic = customProperties.getDeviceKafkaTopic();

        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, json);
        // 处理错误回调
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                ex.printStackTrace();
                kafkaErrorLogger.error("kafka sendMessage error, ex = {}, topic = {}, data = {}", ex, topic, json);
            }
            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                System.out.println("kafka发送消息成功");
            }
        });

        try {
            SendResult<Integer, String> integerStringSendResult = future.get();
            String string = integerStringSendResult.toString();
            System.out.println("string:  " + string);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
