package com.polarico.kafka.repository;

import com.polarico.kafka.model.sys.model.Device;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dxy
 */
@Repository
public interface DeviceRepository extends MongoRepository<Device, ObjectId> {
}
