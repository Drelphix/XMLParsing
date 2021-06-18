package by.demeshko.xmlparser.entity;

public enum GroupOfComponents {
    IO("Input-Output device"),
    multimedia("Multimedia device"),
    components("Component devices"),
    storage("Storage devices"),
    network("Network devices");

    public final String label;

    private GroupOfComponents(String label) {
        this.label = label;
    }
}
