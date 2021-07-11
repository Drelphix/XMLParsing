package by.demeshko.xmlparser.entity;

import by.demeshko.xmlparser.entity.types.CpuArchitecture;
import by.demeshko.xmlparser.entity.types.CpuSocket;
import by.demeshko.xmlparser.entity.types.GroupOfComponents;
import by.demeshko.xmlparser.exception.DeviceException;

public class Cpu extends Device{
    private double energyConsumption;
    private CpuSocket cpuSocket;
    private CpuArchitecture cpuArchitecture;

    public Cpu(String id,
               String deviceName,
               String origin,
               int price,
               boolean critical,
               boolean peripheral,
               GroupOfComponents groupOfComponents,
               int energyConsumption,
               CpuSocket cpuSocket,
               CpuArchitecture cpuArchitecture) {

        super(id, deviceName, origin, price, critical, peripheral, groupOfComponents);
        this.energyConsumption = energyConsumption;
        this.cpuSocket = cpuSocket;
        this.cpuArchitecture = cpuArchitecture;
    }

    private Cpu() {}

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(double energyConsumption) throws DeviceException {
        if(energyConsumption < 0) {
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
        Cpu cpu = (Cpu) o;
        return Double.compare(cpu.energyConsumption, energyConsumption) == 0
                && cpuSocket == cpu.cpuSocket
                && cpuArchitecture == cpu.cpuArchitecture
                && this.getId().equals(cpu.getId())
                && this.getDeviceName().equals(cpu.getDeviceName())
                && this.getOrigin().equals(cpu.getOrigin())
                && this.getPrice() == cpu.getPrice()
                && this.isCritical() == cpu.isCritical()
                && this.isPeripheral() == cpu.isPeripheral()
                && this.getGroupOfComponents().equals(cpu.getGroupOfComponents());
    }

    @Override
    public int hashCode() {
        int hashcode = super.hashCode();
        hashcode = hashcode * (int) energyConsumption;
        hashcode = hashcode * cpuSocket.hashCode();
        hashcode = hashcode * cpuArchitecture.hashCode();
        return hashcode;
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

    public static Builder newBuilder() {
        return new Cpu().new Builder();
    }

    public class Builder{

        private Builder(){
        }
        public Builder setId(String id){
            Cpu.this.setId(id);
            return this;
        }

        public Builder setDeviceName(String deviceName){
            Cpu.this.setDeviceName(deviceName);
            return this;
        }
        public Builder setOrigin(String origin){
            Cpu.this.setOrigin(origin);
            return this;
        }

        public Builder setPrice(int price) throws DeviceException {
            Cpu.this.setPrice(price);
            return this;
        }

        public Builder setCritical(boolean isCritical){
            Cpu.this.setCritical(isCritical);
            return this;
        }

        public Builder setPeripheral(boolean peripheral){
            Cpu.this.setPeripheral(peripheral);
            return this;
        }

        public Builder setGroupOfComponents(GroupOfComponents groupOfComponents){
            Cpu.this.setGroupOfComponents(groupOfComponents);
            return this;
        }

        public Builder setEnergyConsumption(int energyConsumption) throws DeviceException {
            Cpu.this.setEnergyConsumption(energyConsumption);
            return this;
        }

        public Builder setCpuSocket(CpuSocket cpuSocket){
            Cpu.this.setCpuSocket(cpuSocket);
            return this;
        }

        public Builder setCpuArchitecture(CpuArchitecture cpuArchitecture){
            Cpu.this.setCpuArchitecture(cpuArchitecture);
            return this;
        }

        public Cpu build() {
            return Cpu.this;
        }

    }



}
