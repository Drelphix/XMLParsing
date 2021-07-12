package by.demeshko.xmlparser.entity.types;

public enum GroupOfComponents { //TODO 11.07.2021 23:07 : fix names
    IO_DEVICE("Input-Output device"),
    MULTIMEDIA_DEVICE("Multimedia device"),
    COMPONENT_DEVICE("Component devices"),
    STORAGE_DEVICE("Storage devices"),
    NETWORK_DEVICE("Network devices");

    public final String label;

    GroupOfComponents(String label) {
        this.label = label;
    }
}
