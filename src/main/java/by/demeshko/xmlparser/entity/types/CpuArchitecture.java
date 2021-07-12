package by.demeshko.xmlparser.entity.types;

public enum CpuArchitecture { //TODO 11.07.2021 23:07 : fix names
    BROADWELL("Broadwell"),
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
