package by.demeshko.xmlparser.entity;

import by.demeshko.xmlparser.entity.types.GroupOfComponents;
import by.demeshko.xmlparser.entity.types.Port;
import by.demeshko.xmlparser.exception.DeviceException;

import java.time.LocalDate;

public class Videocard extends Device {
    private int energyConsumption;
    private boolean cooler;
    private Port outputPort;
    private int hashRate;

    public Videocard(String id, LocalDate date, String deviceName, String origin, int price, boolean critical,
                     boolean peripheral, GroupOfComponents groupOfComponents, int energyConsumption,
                     boolean cooler, Port outputPort, int hashRate) {
        super(id, date, deviceName, origin, price, critical, peripheral, groupOfComponents);
        this.energyConsumption = energyConsumption;
        this.cooler = cooler;
        this.outputPort = outputPort;
        this.hashRate = hashRate;
    }

    private Videocard() {
    }

    public static Videocard.Builder newBuilder() {
        return new Videocard().new Builder();
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(int energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public boolean isCooler() {
        return cooler;
    }

    public void setCooler(boolean cooler) {
        this.cooler = cooler;
    }

    public Port getOutputPort() {
        return outputPort;
    }

    public void setOutputPort(Port outputPort) {
        this.outputPort = outputPort;
    }

    public int getHashRate() {
        return hashRate;
    }

    public void setHashRate(int hashRate) {
        this.hashRate = hashRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Videocard videocard = (Videocard) o;

        if (energyConsumption != videocard.energyConsumption) return false;
        if (cooler != videocard.cooler) return false;
        if (hashRate != videocard.hashRate) return false;
        return outputPort == videocard.outputPort;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + energyConsumption;
        result = 31 * result + (cooler ? 1 : 0);
        result = 31 * result + (outputPort != null ? outputPort.hashCode() : 0);
        result = 31 * result + hashRate;
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Videocard {").append(super.toString())
                .append(", energyConsumption = ").append(energyConsumption)
                .append(", cooler = ").append(cooler)
                .append(", outputPorts = ").append(outputPort)
                .append(", hashRate = ").append(hashRate)
                .append(" H/s};").toString();
    }

    public class Builder {

        private Builder() {
        }

        public Videocard.Builder setId(String id) {
            Videocard.this.setId(id);
            return this;
        }

        public Videocard.Builder setDate(LocalDate date) {
            Videocard.this.setDate(date);
            return this;
        }

        public Videocard.Builder setDeviceName(String deviceName) {
            Videocard.this.setDeviceName(deviceName);
            return this;
        }

        public Videocard.Builder setOrigin(String origin) {
            Videocard.this.setOrigin(origin);
            return this;
        }

        public Videocard.Builder setPrice(int price) throws DeviceException {
            Videocard.this.setPrice(price);
            return this;
        }

        public Videocard.Builder setCritical(boolean isCritical) {
            Videocard.this.setCritical(isCritical);
            return this;
        }

        public Videocard.Builder setPeripheral(boolean peripheral) {
            Videocard.this.setPeripheral(peripheral);
            return this;
        }

        public Videocard.Builder setGroupOfComponents(GroupOfComponents groupOfComponents) {
            Videocard.this.setGroupOfComponents(groupOfComponents);
            return this;
        }

        public Videocard.Builder setEnergyConsumption(int energyConsumption) throws DeviceException {
            Videocard.this.setEnergyConsumption(energyConsumption);
            return this;
        }

        public Videocard.Builder setCooler(boolean cooler) {
            Videocard.this.setCooler(cooler);
            return this;
        }

        public Videocard.Builder setOutputPort(Port port) {
            Videocard.this.setOutputPort(port);
            return this;
        }

        public Videocard.Builder setHashRate(int hashRate) {
            Videocard.this.setHashRate(hashRate);
            return this;
        }

        public Videocard build() {
            return Videocard.this;
        }

    }
}
