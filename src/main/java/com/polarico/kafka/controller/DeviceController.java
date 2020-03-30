package com.polarico.kafka.controller;

import com.polarico.kafka.constant.LoggerConstant;
import com.polarico.kafka.constant.PromptConstant;
import com.polarico.kafka.model.sys.JsonResponse;
import com.polarico.kafka.producer.DeviceProducer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dxy
 */
@Controller
@RequestMapping(value = "/device")
public class DeviceController {
    private static Logger logger = LoggerFactory.getLogger(DeviceController.class);
    private final DeviceProducer deviceProducer;

    public DeviceController(DeviceProducer deviceProducer) {
        this.deviceProducer = deviceProducer;
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping(value = "/save")
    public Object save(String data) {
        try {
            if (StringUtils.isEmpty(data)) {
                return new JsonResponse(-1, null, PromptConstant.DATA_IS_NULL);
            }
            deviceProducer.sendDataToKafka(data);
        } catch (Exception e) {
            logger.error(LoggerConstant.save, e);
            return e;
        }
        return null;
    }


}
