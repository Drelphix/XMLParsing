package by.demeshko.xmlparser.entity;

enum Port {
    COM("COM"),
    LPT("LPT"),
    USB("USB"),
    PS("PS/2"),
    DVI("DVI"),
    HDMI("HDMI"),
    SATA("SATA"),
    PCI("PCI-E");

    public final String port;

    private Port(String port) {
        this.port = port;
    }
}
