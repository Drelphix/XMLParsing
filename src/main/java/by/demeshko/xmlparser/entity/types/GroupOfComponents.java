package by.demeshko.xmlparser.entity.types;

public enum GroupOfComponents {
    IO_DEVICE("Input-Output device"),
    MULTIMEDIA_DEVICE("Multimedia device"),
    COMPONENT_DEVICE("Component devices"),
    STORAGE_DEVICE("Storage devices"),
    NETWORK_DEVICE("Network devices");

    private final String value;

    GroupOfComponents(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
