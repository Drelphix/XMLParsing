package by.demeshko.xmlparser.parser;

import by.demeshko.xmlparser.entity.Device;
import by.demeshko.xmlparser.exception.DeviceException;

import java.util.List;

public interface DeviceParser {

    List<Device> parseDevices(String xmlFilePath) throws DeviceException;
}
