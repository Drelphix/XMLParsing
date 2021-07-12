package by.demeshko.xmlparser.repository;

import by.demeshko.xmlparser.entity.Device;

public interface DeviceRepository {

    int size();

    boolean add(Device device);

    boolean remove(Device device);

    Device get(int index);

    Device set(int index, Device device);

    void removeAll();
}
