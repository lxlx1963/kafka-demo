package com.polarico.kafka.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.polarico.kafka.config.CustomProperties;
import com.polarico.kafka.constant.PromptConstant;
import com.polarico.kafka.exception.ServiceException;
import com.polarico.kafka.model.sys.model.Device;
import com.polarico.kafka.repository.DeviceRepository;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dxy
 */
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final CustomProperties customProperties;

    public DeviceService(DeviceRepository deviceRepository, CustomProperties customProperties) {
        this.deviceRepository = deviceRepository;
        this.customProperties = customProperties;
    }

    /**
     * 保存设备
     *
     * @param json String
     */
    public void consumer(String json) throws ServiceException {
        if (StringUtils.isEmpty(json)) {
            return;
        }

        List<Device> deviceList = JSON.parseArray(json, Device.class);
        if (CollectionUtils.isEmpty(deviceList)) {
            return;
        }
        Integer mongoBatchSaveSize = customProperties.getMongoBatchSaveSize();
        if (null == mongoBatchSaveSize) {
            throw new ServiceException(PromptConstant.MONGO_BATCH_SAVE_SIZE_IS_NULL);
        }
        // 按照mongoBatchSaveSize将List<Device>分割成各等分的子List<Device>
        List<List<Device>> partitionList = Lists.partition(deviceList, mongoBatchSaveSize);
        for (List<Device> list: partitionList) {
            doSaveBatch(list);
        }
    }

    /**
     * 保存设备
     *
     * @param device Device
     */
    public void save(Device device) {
        deviceRepository.save(device);
    }

    /**
     * 批量保存
     *
     * @param deviceList List<Device>
     */
    public void doSaveBatch(List<Device> deviceList) {
        deviceRepository.saveAll(deviceList);
    }

    /**
     * 查询所有
     *
     * @return List<Device>
     */
    public List<Device> queryAll() {
        return deviceRepository.findAll();
    }

}
