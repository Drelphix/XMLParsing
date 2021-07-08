package by.demeshko.xmlparser.parser;

import by.demeshko.xmlparser.entity.Device;
import by.demeshko.xmlparser.exception.DeviceException;

import java.util.List;

public interface DeviceParser {
    static final String PATH_TO_XML = "./files/devices.xml";

    public void parseDevices(String xmlFilePath) throws DeviceException;
}
