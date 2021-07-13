package by.demeshko.xmlparser.entity;

import by.demeshko.xmlparser.entity.types.GroupOfComponents;
import by.demeshko.xmlparser.exception.DeviceException;

import java.time.LocalDate;

public abstract class Device {
    private String id;
    private LocalDate date;
    private String deviceName;
    private String origin;
    private int price;
    private boolean critical;
    private boolean peripheral;
    private GroupOfComponents groupOfComponents;

    protected Device() {
    }

    protected Device(String id, LocalDate date, String deviceName, String origin, int price, boolean critical, boolean peripheral, GroupOfComponents groupOfComponents) {
        this.id = id;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

        if (price != device.price) return false;
        if (critical != device.critical) return false;
        if (peripheral != device.peripheral) return false;
        return groupOfComponents == device.groupOfComponents &&
                id.equals(device.id) &&
                date.equals(device.date) &&
                deviceName.equals(device.deviceName) &&
                origin.equals(device.origin);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (deviceName != null ? deviceName.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (critical ? 1 : 0);
        result = 31 * result + (peripheral ? 1 : 0);
        result = 31 * result + (groupOfComponents != null ? groupOfComponents.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("id = ").append(id)
                .append(", date = ").append(date)
                .append(", deviceName = ").append(deviceName)
                .append(", origin = ").append(origin)
                .append(", price = ").append(price)
                .append(", isCritical = ").append(critical)
                .append(", isPeripheral = ").append(peripheral)
                .append(", groupOfComponents = ").append(groupOfComponents.getValue());
        return stringBuilder.toString();
    }
}
