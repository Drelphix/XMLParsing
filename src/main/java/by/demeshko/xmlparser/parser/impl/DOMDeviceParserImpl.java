package by.demeshko.xmlparser.parser.impl;

import by.demeshko.xmlparser.entity.Device;
import by.demeshko.xmlparser.exception.DeviceException;
import by.demeshko.xmlparser.parser.DeviceParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DOMDeviceParserImpl implements DeviceParser {
    Logger logger = LogManager.getLogger();

    @Override
    public void parseDevices(String xmlFilePath) throws DeviceException {
        logger.info(!xmlFilePath.equals("") ?
                xmlFilePath = DOMDeviceParserImpl.PATH_TO_XML :
                "Using path: "+xmlFilePath);
        File xmlFile = new File(xmlFilePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new DeviceException("Error parsing with DOM parser", e);
        }

    }
}
