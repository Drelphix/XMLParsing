package by.demeshko.xmlparser.repository.impl;

import by.demeshko.xmlparser.entity.Device;
import by.demeshko.xmlparser.repository.DeviceRepository;

import java.util.ArrayList;
import java.util.List;

public class DeviceRepositoryImpl implements DeviceRepository {
    private List<Device> devices = new ArrayList<>();
    private static final DeviceRepository DEVICE_REPOSITORY = new DeviceRepositoryImpl();

    private DeviceRepositoryImpl(){

    }

    public static DeviceRepository getInstance(){
        return DEVICE_REPOSITORY;
    }

    public int size() {
        return devices.size();
    }

    public boolean add(Device device) {
        return devices.add(device);
    }

    public boolean remove(Device device) {
        return devices.remove(device);
    }

    public Device get(int index) {
        return devices.get(index);
    }

    public Device set(int index, Device device) {
        return devices.set(index, device);
    }
}
