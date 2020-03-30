package com.polarico.kafka;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.polarico.kafka.config.CustomProperties;
import com.polarico.kafka.constant.CommonConstant;
import com.polarico.kafka.model.sys.model.Device;
import com.polarico.kafka.util.DateUtils;
import com.polarico.kafka.util.HttpClientUtils;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoKafkaApplicationTests {
    private static Logger logger = LoggerFactory.getLogger(DemoKafkaApplicationTests.class);
    @Autowired
    private CustomProperties customProperties;

    @Test
    public void doPostRequest() {
        // 默认生成条数
        int size = 10;
        String baseUrl = customProperties.getBaseUrl();
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();

        List<Device> deviceList = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            Device device = new Device();

            device.setProductId(randomDataGenerator.nextLong(1, 100000));
            device.setDeviceId(randomDataGenerator.nextLong(1, 100000));
            device.setDeviceType(randomDataGenerator.nextInt(1, 1000));
            device.setTimeStamp(DateUtils.getTimeStampSecond());
            device.setAlarmLevel(randomDataGenerator.nextInt(1, 10));
            device.setAlarmValue(randomDataGenerator.getRandomGenerator().nextDouble());
            deviceList.add(device);
        }
        // 请求参数
        Map<String, String> paramMap = Maps.newHashMap();
        paramMap.put(CommonConstant.DATA, JSON.toJSONString(deviceList));

        try {
            HttpClientUtils.doPost(baseUrl, paramMap);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("doPostRequest", e);
        }
    }

}
