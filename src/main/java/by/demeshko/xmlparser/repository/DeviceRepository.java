package by.demeshko.xmlparser.repository;

import by.demeshko.xmlparser.entity.Device;

public interface DeviceRepository {

    public DeviceRepository getInstance();

    public int size();

    public boolean add(Device device);

    public boolean remove(Device device);

    public Device get(int index);

    public Device set(int index, Device device);
}
