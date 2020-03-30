package com.polarico.kafka.config;


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
    private String deviceKafkaTopic;
    /**
     * 基础URL
     */
    private String baseUrl;
    /**
     * mongo批量保存条数
     */
    private Integer mongoBatchSaveSize;

    public String getDeviceKafkaTopic() {
        return deviceKafkaTopic;
    }

    public void setDeviceKafkaTopic(String deviceKafkaTopic) {
        this.deviceKafkaTopic = deviceKafkaTopic;
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
