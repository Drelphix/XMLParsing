package by.demeshko.xmlparser.main;

import by.demeshko.xmlparser.exception.DeviceException;
import by.demeshko.xmlparser.parser.DeviceParser;
import by.demeshko.xmlparser.parser.impl.DOMDeviceParserImpl;
import by.demeshko.xmlparser.repository.DeviceRepository;
import by.demeshko.xmlparser.repository.impl.DeviceRepositoryImpl;

public class Application {
    public static void main(String[] args) throws DeviceException {
        DeviceParser deviceParser = new DOMDeviceParserImpl();
        deviceParser.parseDevices("");
        DeviceRepository deviceRepository = DeviceRepositoryImpl.getInstance();
        System.out.println(deviceRepository.size());
        for (int i = 0; i < deviceRepository.size(); i++) {
            System.out.println(deviceRepository.get(i));
        }
    }
}
