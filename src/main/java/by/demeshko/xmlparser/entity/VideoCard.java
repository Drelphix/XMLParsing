package by.demeshko.xmlparser.entity;

import by.demeshko.xmlparser.entity.types.GroupOfComponents;
import by.demeshko.xmlparser.entity.types.Port;
import by.demeshko.xmlparser.exception.DeviceException;

import java.util.Objects;

public class VideoCard extends Device{ //TODO 10.07.2021 14:29 :
    private int energyConsumption;
    private boolean cooler;
    private Port outputPort;
    private int hashRate;

    public VideoCard(String id, String deviceName, String origin, int price, boolean critical,
                     boolean peripheral, GroupOfComponents groupOfComponents, int energyConsumption,
                     boolean cooler, Port outputPort, int hashRate) {
        super(id, deviceName, origin, price, critical, peripheral, groupOfComponents);
        this.energyConsumption = energyConsumption;
        this.cooler = cooler;
        this.outputPort = outputPort;
        this.hashRate = hashRate;
    }

    private VideoCard() {}

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

    public static VideoCard.Builder newBuilder() {
        return new VideoCard().new Builder();
    }

    public class Builder{

        private Builder(){
        }
        public VideoCard.Builder setId(String id){
            VideoCard.this.setId(id);
            return this;
        }

        public VideoCard.Builder setDeviceName(String deviceName){
            VideoCard.this.setDeviceName(deviceName);
            return this;
        }
        public VideoCard.Builder setOrigin(String origin){
            VideoCard.this.setOrigin(origin);
            return this;
        }

        public VideoCard.Builder setPrice(int price) throws DeviceException {
            VideoCard.this.setPrice(price);
            return this;
        }

        public VideoCard.Builder setCritical(boolean isCritical){
            VideoCard.this.setCritical(isCritical);
            return this;
        }

        public VideoCard.Builder setPeripheral(boolean peripheral){
            VideoCard.this.setPeripheral(peripheral);
            return this;
        }

        public VideoCard.Builder setGroupOfComponents(GroupOfComponents groupOfComponents){
            VideoCard.this.setGroupOfComponents(groupOfComponents);
            return this;
        }

        public VideoCard.Builder setEnergyConsumption(int energyConsumption) throws DeviceException {
            VideoCard.this.setEnergyConsumption(energyConsumption);
            return this;
        }

        public VideoCard.Builder setCooler(boolean cooler){
            VideoCard.this.setCooler(cooler);
            return this;
        }

        public VideoCard.Builder setOutputPort(Port port){
            VideoCard.this.setOutputPort(port);
            return this;
        }

        public VideoCard.Builder setHashRate(int hashRate){
            VideoCard.this.setHashRate(hashRate);
            return this;
        }

        public VideoCard build() {
            return VideoCard.this;
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoCard videoCard = (VideoCard) o;
        return energyConsumption == videoCard.energyConsumption &&
                cooler == videoCard.cooler && hashRate == videoCard.hashRate &&
                outputPort.equals(videoCard.outputPort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(energyConsumption, cooler,
                outputPort, hashRate);
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
}
