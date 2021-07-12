package by.demeshko.xmlparser.parser;

public enum XMLTag {
    DEVICES("devices"),
    DEVICE_NAME("deviceName"),
    ORIGIN("origin"),
    PRICE("price"),
    CRITICAL("critical"),
    COMPONENT_GROUP("groupOfComponents"),
    PERIPHERAL("peripheral"),
    POWER_CONSUMPTION("powerConsumption"),
    COOLER("cooler"),
    OUTPUT_PORT("outputPort"),
    HASHRATE("hashRate"),
    CPU_SOCKET("cpuSocket"),
    MEMORY_TYPE("memoryType"),
    ARCHITECTURE("architecture"),
    MOTHERBOARD("motherboard"),
    CPU("cpu"),
    VIDEOCARD("videocard"),
    SATA("sata");

    private String value;

    XMLTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
