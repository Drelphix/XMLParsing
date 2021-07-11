package by.demeshko.xmlparser.entity.types;

public enum CpuArchitecture { //TODO 11.07.2021 23:07 : fix names
    Broadwell("Broadwell"),
    Haswell("Haswell"),
    SandyBridge("Sandy Bridge"),
    CannonLake("Cannon Lake"),
    Skylake("Skylake");

    private String value;

    CpuArchitecture(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
