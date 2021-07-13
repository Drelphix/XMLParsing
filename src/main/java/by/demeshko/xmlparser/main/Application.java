package by.demeshko.xmlparser.main;

import by.demeshko.xmlparser.entity.Device;
import by.demeshko.xmlparser.exception.DeviceException;
import by.demeshko.xmlparser.parser.DeviceParser;
import by.demeshko.xmlparser.parser.impl.DOMDeviceParserImpl;
import by.demeshko.xmlparser.parser.impl.SaXXmlParserImpl;
import by.demeshko.xmlparser.parser.impl.StaXXmlParserImpl;

import java.util.List;

public class Application {
    public static void main(String[] args) throws DeviceException {
        DeviceParser domDeviceParser = new DOMDeviceParserImpl();
        List<Device> DOMParserList = domDeviceParser.parseDevices("");
        System.out.println("List of devices by DOM parser:");
        for (Device device : DOMParserList) {
            System.out.println(device);
        }
        DeviceParser saxDeviceParser = new SaXXmlParserImpl();
        List<Device> SaXParserList = saxDeviceParser.parseDevices("");
        System.out.println("List of devices by SaX parser:");
        for (Device device : SaXParserList) {
            System.out.println(device);
        }
        DeviceParser staxDeviceParser = new StaXXmlParserImpl();
        List<Device> StaXParserList = staxDeviceParser.parseDevices("");
        System.out.println("List of devices by StAX parser");
        for (Device device : StaXParserList) {
            System.out.println(device);
        }

    }
}
