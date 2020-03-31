package com.du.kafka.consumer;

import com.du.kafka.exception.ServiceException;
import com.du.kafka.service.DeviceService;
import com.du.kafka.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author dxy
 */
@Component
public class DeviceConsumer {
    private final static Logger logger = LoggerFactory.getLogger(DeviceConsumer.class);

    private final DeviceService deviceService;
    public DeviceConsumer(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @KafkaListener(groupId = "#{'${kafka.consumer.device.group.id}'}", topics = "#{'${kafka.consumer.device.topics}'}")
    private void transformDataFromKafkaToMongo(String message, Acknowledgment ack) {
        try {
            deviceService.consumer(message);
            ack.acknowledge();
            System.out.println(DateUtils.getDateTime("yyyy-MM-dd HH:mm:ss.SSS") + "-成功消费消息-message: "+ message);
        } catch (ServiceException e) {
            logger.error("transformDataFromKafkaToMongo", e);
        }
    }

}
