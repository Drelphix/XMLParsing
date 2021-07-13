package by.demeshko.xmlparser.parser.impl;

import by.demeshko.xmlparser.entity.Device;
import by.demeshko.xmlparser.exception.DeviceException;
import by.demeshko.xmlparser.parser.DeviceParser;
import by.demeshko.xmlparser.parser.handler.DeviceHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaXXmlParserImpl implements DeviceParser {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PATH_TO_XML = "devices.xml";


    @Override
    public List<Device> parseDevices(String xmlFilePath) throws DeviceException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        LOGGER.info("Using file: " + (xmlFilePath.equals("") ?
                xmlFilePath = PATH_TO_XML :
                xmlFilePath));
        try {
            SAXParser saxParser = factory.newSAXParser();
            DeviceHandler handler = new DeviceHandler();
            String xmlFile = ClassLoader.getSystemClassLoader().getResource(xmlFilePath).getFile();
            saxParser.parse(xmlFile, handler);
            return handler.getDevices();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new DeviceException("SaX parser error: ", e);
        }
    }

}
