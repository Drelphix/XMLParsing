package by.demeshko.xmlparser.entity;

import by.demeshko.xmlparser.entity.Port;

import java.util.List;

public class Type {
    private boolean isPeripheral;
    private int powerConsumption;
    private boolean withCooler;
    private GroupOfComponents groupOfComponents;

    public Type(boolean isPeripheral, int powerConsumption, boolean withCooler, GroupOfComponents groupOfComponents) {
        this.isPeripheral = isPeripheral;
        this.powerConsumption = powerConsumption;
        this.withCooler = withCooler;
        this.groupOfComponents = groupOfComponents;
    }

    public boolean isPeripheral() {
        return isPeripheral;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public boolean isWithCooler() {
        return withCooler;
    }

    public GroupOfComponents getGroupOfComponents() {
        return groupOfComponents;
    }

    @Override
    public String toString(){
        return new StringBuilder().append("Peripheral: ").append(isPeripheral)
                .append("/nPower Consumption: ").append(powerConsumption).append("watt/n")
                .append("Cooler: ").append(withCooler)
                .append("Group of components: ").append(groupOfComponents.label).toString();
    }

    public boolean equals(Type type){
        if(type == this){
            return true;
        }
        if(type == null){
            return false;
        }
        return (type.isPeripheral() == this.isPeripheral) &&
                (type.getPowerConsumption() == this.powerConsumption) &&
                (type.withCooler == this.withCooler) &&
                (type.groupOfComponents == this.groupOfComponents);
    }
}
