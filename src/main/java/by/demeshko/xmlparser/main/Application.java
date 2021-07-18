package by.demeshko.xmlparser.main;

import by.demeshko.xmlparser.entity.Device;
import by.demeshko.xmlparser.exception.DeviceException;
import by.demeshko.xmlparser.parser.DeviceParser;
import by.demeshko.xmlparser.parser.impl.DOMDeviceParserImpl;
import by.demeshko.xmlparser.parser.impl.SaXXmlParserImpl;
import by.demeshko.xmlparser.parser.impl.StaXXmlParserImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Application {
    public static void main(String[] args) throws DeviceException {
        Logger logger = LogManager.getLogger();
        DeviceParser domDeviceParser = new DOMDeviceParserImpl();
        List<Device> domParserList = domDeviceParser.parseDevices("");
        logger.info("List of devices by DOM parser:");
        for (Device device : domParserList) {
            logger.info(device);
        }
        DeviceParser saxDeviceParser = new SaXXmlParserImpl();
        List<Device> saxParserList = saxDeviceParser.parseDevices("");
        logger.info("List of devices by SaX parser:");
        for (Device device : saxParserList) {
            logger.info(device);
        }
        DeviceParser staxDeviceParser = new StaXXmlParserImpl();
        List<Device> staxParserList = staxDeviceParser.parseDevices("");
        logger.info("List of devices by StAX parser");
        for (Device device : staxParserList) {
            logger.info(device);
        }

    }
}
