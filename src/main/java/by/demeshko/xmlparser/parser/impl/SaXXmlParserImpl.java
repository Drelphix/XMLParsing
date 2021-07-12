package by.demeshko.xmlparser.parser.impl;

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

public class SaXXmlParserImpl implements DeviceParser {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void parseDevices(String xmlFilePath) throws DeviceException { //TODO 11.07.2021 23:07 :
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            DeviceHandler handler = new DeviceHandler();
            saxParser.parse(SaXXmlParserImpl.PATH_TO_XML, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new DeviceException("SaX parser error: ", e);
        }
    }

}
