package by.demeshko.xmlparser.parser;

import by.demeshko.xmlparser.exception.DeviceException;

public interface DeviceParser {
    String PATH_TO_XML = "devices.xml";

    void parseDevices(String xmlFilePath) throws DeviceException;
}
