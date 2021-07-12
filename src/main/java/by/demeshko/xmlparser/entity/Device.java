package by.demeshko.xmlparser.entity;

import by.demeshko.xmlparser.entity.types.GroupOfComponents;
import by.demeshko.xmlparser.exception.DeviceException;

public abstract class Device {
    private String id;
    private String deviceName;
    private String origin;
    private int price;
    private boolean critical;
    private boolean peripheral;
    private GroupOfComponents groupOfComponents;

    public Device() {
    }

    public Device(String id, String deviceName, String origin, int price, boolean critical, boolean peripheral, GroupOfComponents groupOfComponents) {
        this.id = id;
        this.deviceName = deviceName;
        this.origin = origin;
        this.price = price;
        this.critical = critical;
        this.peripheral = peripheral;
        this.groupOfComponents = groupOfComponents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) throws DeviceException {
        if (price < 0) {
            throw new DeviceException("Price can't be less than 0! Current value is: " + price);
        }
        this.price = price;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public boolean isPeripheral() {
        return peripheral;
    }

    public void setPeripheral(boolean peripheral) {
        this.peripheral = peripheral;
    }

    public GroupOfComponents getGroupOfComponents() {
        return groupOfComponents;
    }

    public void setGroupOfComponents(GroupOfComponents groupOfComponents) {
        this.groupOfComponents = groupOfComponents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return price == device.price
                && critical == device.critical
                && peripheral == device.peripheral
                && id.equals(device.id)
                && deviceName.equals(device.deviceName)
                && origin.equals(device.origin)
                && groupOfComponents == device.groupOfComponents;
    }

    @Override
    public int hashCode() {
        int hashcode = 40;
        hashcode = hashcode * this.id.hashCode();
        hashcode = hashcode * this.deviceName.hashCode();
        hashcode = hashcode * this.origin.hashCode();
        hashcode = hashcode * this.price;
        hashcode = hashcode * (this.critical ? 40 : 20);
        hashcode = hashcode * (this.peripheral ? 40 : 20);
        hashcode = hashcode * this.groupOfComponents.hashCode();
        return hashcode;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id = ").append(id)
                .append(", deviceName = ").append(deviceName)
                .append(", origin = ").append(origin)
                .append(", price = ").append(price)
                .append(", isCritical = ").append(critical)
                .append(", isPeripheral = ").append(peripheral)
                .append(", groupOfComponents = ").append(groupOfComponents.label);
        return stringBuilder.toString();
    }
}
