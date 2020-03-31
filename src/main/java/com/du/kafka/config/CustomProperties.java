package com.du.kafka.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dxy
 */
@Component
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {
    /**
     * 设备Kafka主题
     */
    @Value("${kafka.producer.device.topic}")
    private String producerTopic;
    /**
     * 基础URL
     */
    private String baseUrl;
    /**
     * mongo批量保存条数
     */
    private Integer mongoBatchSaveSize;

    public String getProducerTopic() {
        return producerTopic;
    }

    public void setProducerTopic(String producerTopic) {
        this.producerTopic = producerTopic;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Integer getMongoBatchSaveSize() {
        return mongoBatchSaveSize;
    }

    public void setMongoBatchSaveSize(Integer mongoBatchSaveSize) {
        this.mongoBatchSaveSize = mongoBatchSaveSize;
    }
}
