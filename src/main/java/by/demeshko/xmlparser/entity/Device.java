package by.demeshko.xmlparser.entity;

import java.util.List;
import java.util.Locale.IsoCountryCode;

public class Device {
    private long idDevice;
    private String name;
    private IsoCountryCode origin;
    private int price;
    private Type type;
    private boolean isCritical;
    private List<Port> ports;

    public Device(long idDevice, String name, IsoCountryCode origin, int price, Type type, boolean isCritical, List<Port> ports) {
        this.idDevice = idDevice;
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.type = type;
        this.isCritical = isCritical;
        this.ports = ports;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(IsoCountryCode origin) {
        this.origin = origin;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public long getIdDevice() {
        return idDevice;
    }

    public String getName() {
        return name;
    }

    public IsoCountryCode getOrigin() {
        return origin;
    }

    public int getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void deletePort(Port port){
        ports.remove(port);
    }

    public void addPort(Port port){
        this.ports.add(port);
    }

    @Override
    public String toString(){
        return new StringBuilder().append("ID: ").append(idDevice)
                .append("/nName: ").append(name)
                .append("/nCountry: ").append(origin)
                .append("/nPrice: ").append(price)
                .append("/nType: ").append(type.toString())
                .append("/nCritical: ").append(isCritical)
                .append("/nPorts: ").append(ports).toString();
    }

    public boolean equals(Device device){
        if(device == this){
            return true;
        }
        if(device == null){
            return false;
        }
        return (device.getIdDevice() == this.idDevice) &&
                (device.getName().equals(this.name)) &&
                (device.getOrigin() == this.origin) &&
                (device.getPrice() == this.price) &&
                (device.isCritical == this.isCritical) &&
                (device.type.equals(this.type)) &&
                (device.ports.equals(ports));
    }
}
