package by.demeshko.xmlparser.entity;

import by.demeshko.xmlparser.entity.types.CpuSocket;
import by.demeshko.xmlparser.entity.types.GroupOfComponents;
import by.demeshko.xmlparser.entity.types.MemoryType;
import by.demeshko.xmlparser.exception.DeviceException;

import java.time.LocalDate;

public class Motherboard extends Device {
    private CpuSocket socket;
    private MemoryType memoryType;
    private int sata;

    private Motherboard() {
    }

    public Motherboard(String id, LocalDate date, String deviceName, String origin, int price,
                       boolean critical, boolean peripheral, GroupOfComponents groupOfComponents,
                       CpuSocket socket, MemoryType memoryType, int outputPorts) {
        super(id, date, deviceName, origin, price, critical, peripheral, groupOfComponents);
        this.socket = socket;
        this.memoryType = memoryType;
        this.sata = outputPorts;
    }

    public static Motherboard.Builder newBuilder() {
        return new Motherboard().new Builder();
    }

    public CpuSocket getSocket() {
        return socket;
    }

    public void setSocket(CpuSocket socket) {
        this.socket = socket;
    }

    public MemoryType getMemory() {
        return memoryType;
    }

    public void setMemory(MemoryType memoryType) {
        this.memoryType = memoryType;
    }

    public int getSata() {
        return sata;
    }

    public void setSata(int sata) throws DeviceException {
        if (sata < 0) {
            throw new DeviceException();
        }
        this.sata = sata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Motherboard that = (Motherboard) o;

        if (sata != that.sata) return false;
        if (socket != that.socket) return false;
        return memoryType == that.memoryType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (socket != null ? socket.hashCode() : 0);
        result = 31 * result + (memoryType != null ? memoryType.hashCode() : 0);
        result = 31 * result + sata;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Motherboard {").append(super.toString())
                .append(", socket = ").append(this.socket)
                .append(", memoryType = ").append(memoryType)
                .append(", sata = ").append(sata)
                .append("};");
        return stringBuilder.toString();
    }

    public class Builder {

        private Builder() {
        }

        public Motherboard.Builder setId(String id) {
            Motherboard.this.setId(id);
            return this;
        }

        public Motherboard.Builder setDate(LocalDate date) {
            Motherboard.this.setDate(date);
            return this;
        }

        public Motherboard.Builder setDeviceName(String deviceName) {
            Motherboard.this.setDeviceName(deviceName);
            return this;
        }

        public Motherboard.Builder setOrigin(String origin) {
            Motherboard.this.setOrigin(origin);
            return this;
        }

        public Motherboard.Builder setPrice(int price) throws DeviceException {
            Motherboard.this.setPrice(price);
            return this;
        }

        public Motherboard.Builder setCritical(boolean isCritical) {
            Motherboard.this.setCritical(isCritical);
            return this;
        }

        public Motherboard.Builder setPeripheral(boolean peripheral) {
            Motherboard.this.setPeripheral(peripheral);
            return this;
        }

        public Motherboard.Builder setGroupOfComponents(GroupOfComponents groupOfComponents) {
            Motherboard.this.setGroupOfComponents(groupOfComponents);
            return this;
        }

        public Motherboard.Builder setSocket(CpuSocket socket) {
            Motherboard.this.setSocket(socket);
            return this;
        }

        public Motherboard.Builder setMemory(MemoryType memoryType) {
            Motherboard.this.setMemory(memoryType);
            return this;
        }

        public Motherboard.Builder setSata(int sata) throws DeviceException {
            Motherboard.this.setSata(sata);
            return this;
        }

        public Motherboard build() {
            return Motherboard.this;
        }

    }
}
