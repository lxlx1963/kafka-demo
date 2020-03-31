package com.du.kafka.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author dxy
 */
@Document(value = "demo_device")
public class Device implements Serializable {
    private static final long serialVersionUID = 6296776337109249927L;
    /**
     * 主键
     */
    @Id
    private ObjectId id;
    /**
     * 产品ID
     */
    @Indexed(direction = IndexDirection.ASCENDING)
    private Long productId;
    /**
     * 设备ID
     */
    @Indexed(direction = IndexDirection.ASCENDING)
    private Long deviceId;
    /**
     * 设备类型
     */
    @Indexed(direction = IndexDirection.ASCENDING)
    private Integer deviceType;
    /**
     * 时间戳
     */
    @Indexed(direction = IndexDirection.ASCENDING)
    private Long timeStamp;
    /**
     * 报警层级
     */
    private Integer alarmLevel;
    /**
     * 报警值
     */
    private Double alarmValue;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Integer alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Double getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(Double alarmValue) {
        this.alarmValue = alarmValue;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", productId=" + productId +
                ", deviceId=" + deviceId +
                ", deviceType=" + deviceType +
                ", timeStamp=" + timeStamp +
                ", alarmLevel=" + alarmLevel +
                ", alarmValue=" + alarmValue +
                '}';
    }
}
