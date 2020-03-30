package com.polarico.kafka.consumer;

import com.polarico.kafka.exception.ServiceException;
import com.polarico.kafka.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
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

    @KafkaListener(topics = {"test"})
    private void transformDataFromKafkaToMongo(String message) {
        try {
            long start = System.currentTimeMillis();
            deviceService.consumer(message);
            long end = System.currentTimeMillis();
            System.out.println("end - start" + (end - start));
            System.out.println("消费消息-message: "+ message);
        } catch (ServiceException e) {
            logger.error("transformDataFromKafkaToMongo", e);
        }
    }

}
