package by.demeshko.xmlparser.entity;

public enum Memory {
    DDR1("DDR-1"),
    DDR2("DDR-2"),
    DDR3("DDR-3"),
    DDR4("DDR-4"),
    DDR5("DDR-5");

    public final String label;

    private Memory(String label) {
        this.label = label;
    }
}
