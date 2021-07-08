package by.demeshko.xmlparser.entity;

public enum Architecture {
    BW("Broadwell"),
    HW("Haswell"),
    SB("Sandy Bridge"),
    CL("Cannon Lake"),
    SL("Skylake");

    public final String label;

    private Architecture(String label) {
        this.label = label;
    }
}
