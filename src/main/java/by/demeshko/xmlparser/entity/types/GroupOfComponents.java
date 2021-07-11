package by.demeshko.xmlparser.entity.types;

public enum GroupOfComponents { //TODO 11.07.2021 23:07 : fix names
    IODevice("Input-Output device"),
    multimediaDevice("Multimedia device"),
    componentDevice("Component devices"),
    storageDevice("Storage devices"),
    networkDevice("Network devices");

    public final String label;

    private GroupOfComponents(String label) {
        this.label = label;
    }
}
