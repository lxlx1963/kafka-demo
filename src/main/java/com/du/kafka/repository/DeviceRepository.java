package com.du.kafka.repository;

import com.du.kafka.model.Device;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dxy
 */
@Repository
public interface DeviceRepository extends MongoRepository<Device, ObjectId> {
}
