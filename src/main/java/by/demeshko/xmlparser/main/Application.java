package by.demeshko.xmlparser.main;

import by.demeshko.xmlparser.exception.DeviceException;
import by.demeshko.xmlparser.parser.DeviceParser;
import by.demeshko.xmlparser.parser.impl.DOMDeviceParserImpl;
import by.demeshko.xmlparser.parser.impl.SaXXmlParserImpl;
import by.demeshko.xmlparser.repository.DeviceRepository;
import by.demeshko.xmlparser.repository.impl.DeviceRepositoryImpl;

public class Application {
    public static void main(String[] args) throws DeviceException {
        DeviceParser domDeviceParser = new DOMDeviceParserImpl();
        domDeviceParser.parseDevices("");
        DeviceRepository deviceRepository = DeviceRepositoryImpl.getInstance();
        System.out.println("List of devices by DOM parser:");
        for (int i = 0; i < deviceRepository.size(); i++) {
            System.out.println(deviceRepository.get(i));
        }
        deviceRepository.removeAll();
        DeviceParser saxDeviceParser = new SaXXmlParserImpl();
        saxDeviceParser.parseDevices("");
        System.out.println("List of devices by SaX parser:");
        for (int i = 0; i < deviceRepository.size(); i++) {
            System.out.println(deviceRepository.get(i));
        }

    }
}
