package by.demeshko.xmlparser.entity.types;

public enum CpuArchitecture {
    HASWELL("Haswell"),
    SANDY_BRIDGE("Sandy Bridge"),
    CANNON_LAKE("Cannon Lake"),
    SKYLAKE("Skylake");

    private final String value;

    CpuArchitecture(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
