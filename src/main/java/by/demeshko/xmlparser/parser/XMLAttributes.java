package by.demeshko.xmlparser.parser;

public enum XMLAttributes {
    ID("id");


    private String value;

    XMLAttributes(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
