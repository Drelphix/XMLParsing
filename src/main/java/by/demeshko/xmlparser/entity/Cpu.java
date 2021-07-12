package by.demeshko.xmlparser.entity;

import by.demeshko.xmlparser.entity.types.CpuArchitecture;
import by.demeshko.xmlparser.entity.types.CpuSocket;
import by.demeshko.xmlparser.entity.types.GroupOfComponents;
import by.demeshko.xmlparser.exception.DeviceException;

import java.time.LocalDate;

public class Cpu extends Device {
    private double energyConsumption;
    private CpuSocket cpuSocket;
    private CpuArchitecture cpuArchitecture;

    public Cpu(String id,
               String deviceName,
               LocalDate date,
               String origin,
               int price,
               boolean critical,
               boolean peripheral,
               GroupOfComponents groupOfComponents,
               int energyConsumption,
               CpuSocket cpuSocket,
               CpuArchitecture cpuArchitecture) {

        super(id, date, deviceName, origin, price, critical, peripheral, groupOfComponents);
        this.energyConsumption = energyConsumption;
        this.cpuSocket = cpuSocket;
        this.cpuArchitecture = cpuArchitecture;
    }

    private Cpu() {
    }

    public static Builder newBuilder() {
        return new Cpu().new Builder();
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(double energyConsumption) throws DeviceException {
        if (energyConsumption < 0) {
            throw new DeviceException("Energy consumption can't be zero or less");
        }
        this.energyConsumption = energyConsumption;
    }

    public CpuSocket getCpuSocket() {
        return cpuSocket;
    }

    public void setCpuSocket(CpuSocket cpuSocket) {
        this.cpuSocket = cpuSocket;
    }

    public CpuArchitecture getCpuArchitecture() {
        return cpuArchitecture;
    }

    public void setCpuArchitecture(CpuArchitecture cpuArchitecture) {
        this.cpuArchitecture = cpuArchitecture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Cpu cpu = (Cpu) o;

        if (Double.compare(cpu.energyConsumption, energyConsumption) != 0) return false;
        if (cpuSocket != cpu.cpuSocket) return false;
        return cpuArchitecture == cpu.cpuArchitecture;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(energyConsumption);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (cpuSocket != null ? cpuSocket.hashCode() : 0);
        result = 31 * result + (cpuArchitecture != null ? cpuArchitecture.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cpu {").append(super.toString())
                .append(", energyConsumption = ").append(this.energyConsumption)
                .append(" wt/h, cpuSocket = ").append(this.cpuSocket)
                .append(", cpuArchitecture = ").append(this.cpuArchitecture.getValue())
                .append("};");
        return stringBuilder.toString();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(String id) {
            Cpu.this.setId(id);
            return this;
        }

        public Builder setDate(LocalDate date) {
            Cpu.this.setDate(date);
            return this;
        }

        public Builder setDeviceName(String deviceName) {
            Cpu.this.setDeviceName(deviceName);
            return this;
        }

        public Builder setOrigin(String origin) {
            Cpu.this.setOrigin(origin);
            return this;
        }

        public Builder setPrice(int price) throws DeviceException {
            Cpu.this.setPrice(price);
            return this;
        }

        public Builder setCritical(boolean isCritical) {
            Cpu.this.setCritical(isCritical);
            return this;
        }

        public Builder setPeripheral(boolean peripheral) {
            Cpu.this.setPeripheral(peripheral);
            return this;
        }

        public Builder setGroupOfComponents(GroupOfComponents groupOfComponents) {
            Cpu.this.setGroupOfComponents(groupOfComponents);
            return this;
        }

        public Builder setEnergyConsumption(int energyConsumption) throws DeviceException {
            Cpu.this.setEnergyConsumption(energyConsumption);
            return this;
        }

        public Builder setCpuSocket(CpuSocket cpuSocket) {
            Cpu.this.setCpuSocket(cpuSocket);
            return this;
        }

        public Builder setCpuArchitecture(CpuArchitecture cpuArchitecture) {
            Cpu.this.setCpuArchitecture(cpuArchitecture);
            return this;
        }

        public Cpu build() {
            return Cpu.this;
        }

    }


}
