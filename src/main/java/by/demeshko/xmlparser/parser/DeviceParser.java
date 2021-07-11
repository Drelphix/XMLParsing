package by.demeshko.xmlparser.parser;

import by.demeshko.xmlparser.exception.DeviceException;

public interface DeviceParser {
    static final String PATH_TO_XML = "files/devices.xml";

    void parseDevices(String xmlFilePath) throws DeviceException;
}
