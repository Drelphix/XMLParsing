package by.demeshko.xmlparser.parser.impl;

import by.demeshko.xmlparser.entity.Cpu;
import by.demeshko.xmlparser.entity.Motherboard;
import by.demeshko.xmlparser.entity.Videocard;
import by.demeshko.xmlparser.entity.types.*;
import by.demeshko.xmlparser.exception.DeviceException;
import by.demeshko.xmlparser.parser.DeviceParser;
import by.demeshko.xmlparser.parser.XMLAttributes;
import by.demeshko.xmlparser.repository.DeviceRepository;
import by.demeshko.xmlparser.repository.impl.DeviceRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static by.demeshko.xmlparser.parser.XMLTag.*;

public class DOMDeviceParserImpl implements DeviceParser {
    private static final int DEFAULT_POSITION = 0;
    private static final String INCORRECT_PARAMETER_ERROR = "Input parameter is not correct";
    private static final Logger logger = LogManager.getLogger();
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";

    @Override
    public void parseDevices(String xmlFilePath) throws DeviceException {
        logger.info(xmlFilePath.equals("") ?
                xmlFilePath = DOMDeviceParserImpl.PATH_TO_XML :
                "Using path: " + xmlFilePath);
        File xmlFile = new File(xmlFilePath);
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            logger.info("DOM parser start to parse.");
            NodeList cpuList = document.getElementsByTagName(CPU.getValue());
            parseCpu(cpuList);
            NodeList motherboards = document.getElementsByTagName(MOTHERBOARD.getValue());
            parseMotherboard(motherboards);
            NodeList videoCards = document.getElementsByTagName(VIDEOCARD.getValue());
            parseVideoCard(videoCards);
            logger.info("Parsing finished.");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new DeviceException("DOM parser error: ", e);
        }
    }


    private void parseCpu(NodeList cpuList) {
        DeviceRepository deviceRepository = DeviceRepositoryImpl.getInstance();
        for (int i = 0; i < cpuList.getLength(); i++) {
            Element cpuElement = (Element) cpuList.item(i);
            try {
                Cpu cpu = Cpu.newBuilder().setId(cpuElement.getAttribute(XMLAttributes.ID.name()))
                        .setDeviceName(getContent(cpuElement, DEVICE_NAME.name()))
                        .setCpuArchitecture(CpuArchitecture.valueOf(getContent(cpuElement, ARCHITECTURE.name())))
                        .setCpuSocket(CpuSocket.valueOf(getContent(cpuElement, CPU_SOCKET.name())))
                        .setCritical(Boolean.parseBoolean(getContent(cpuElement, CRITICAL.name())))
                        .setEnergyConsumption(Integer.parseInt(getContent(cpuElement, POWER_CONSUMPTION.name())))
                        .setGroupOfComponents(GroupOfComponents.valueOf(getContent(cpuElement, COMPONENT_GROUP.name())))
                        .setOrigin(getContent(cpuElement, ORIGIN.name()))
                        .setPeripheral(Boolean.parseBoolean(getContent(cpuElement, PERIPHERAL.name())))
                        .setPrice(Integer.parseInt(getContent(cpuElement, PRICE.name())))
                        .build();
                deviceRepository.add(cpu);
            } catch (DeviceException e) {
                logger.error(INCORRECT_PARAMETER_ERROR, e);
            }
        }
    }

    private void parseVideoCard(NodeList videoCards) {
        DeviceRepository deviceRepository = DeviceRepositoryImpl.getInstance();
        for (int i = 0; i < videoCards.getLength(); i++) {
            Element videoCardElement = (Element) videoCards.item(i);
            try {
                Videocard videoCard = Videocard.newBuilder()
                        .setId(videoCardElement.getAttribute(XMLAttributes.ID.name()))
                        .setDeviceName(getContent(videoCardElement, DEVICE_NAME.name()))
                        .setCritical(Boolean.parseBoolean(getContent(videoCardElement, CRITICAL.name())))
                        .setEnergyConsumption(Integer.parseInt(getContent(videoCardElement, POWER_CONSUMPTION.name())))
                        .setGroupOfComponents(GroupOfComponents.valueOf(getContent(videoCardElement, COMPONENT_GROUP.name())))
                        .setOrigin(getContent(videoCardElement, ORIGIN.name()))
                        .setPeripheral(Boolean.parseBoolean(getContent(videoCardElement, PERIPHERAL.name())))
                        .setPrice(Integer.parseInt(getContent(videoCardElement, PRICE.name())))
                        .setOutputPort(Port.valueOf(getContent(videoCardElement, OUTPUT_PORT.name())))
                        .setCooler(Boolean.parseBoolean(getContent(videoCardElement, COOLER.name())))
                        .setHashRate(Integer.parseInt(getContent(videoCardElement, HASHRATE.name())))
                        .build();
                deviceRepository.add(videoCard);
            } catch (DeviceException e) {
                logger.error(INCORRECT_PARAMETER_ERROR, e);
            }
        }
    }

    private void parseMotherboard(NodeList motherboards) {
        DeviceRepository deviceRepository = DeviceRepositoryImpl.getInstance();
        for (int i = 0; i < motherboards.getLength(); i++) {
            Element motherboardElement = (Element) motherboards.item(i);
            try {
                Motherboard motherboard = Motherboard.newBuilder()
                        .setId(motherboardElement.getAttribute(XMLAttributes.ID.name()))
                        .setDeviceName(getContent(motherboardElement, DEVICE_NAME.name()))
                        .setCritical(Boolean.parseBoolean(getContent(motherboardElement, CRITICAL.name())))
                        .setGroupOfComponents(GroupOfComponents.valueOf(getContent(motherboardElement, COMPONENT_GROUP.name())))
                        .setOrigin(getContent(motherboardElement, ORIGIN.name()))
                        .setPeripheral(Boolean.parseBoolean(getContent(motherboardElement, PERIPHERAL.name())))
                        .setPrice(Integer.parseInt(getContent(motherboardElement, PRICE.name())))
                        .setSocket(CpuSocket.valueOf(getContent(motherboardElement, CPU_SOCKET.name())))
                        .setSata(Integer.parseInt(getContent(motherboardElement, SATA.name())))
                        .setMemory(MemoryType.valueOf(getContent(motherboardElement, MEMORY_TYPE.name())))
                        .build();
                deviceRepository.add(motherboard);
            } catch (DeviceException e) {
                logger.error(INCORRECT_PARAMETER_ERROR, e);
            }
        }
    }

    private String getContent(Element element, String type) {
        type = type.replace(UNDERSCORE, HYPHEN).toLowerCase();
        NodeList nodeList = element.getElementsByTagName(type);
        Node nodeElement = nodeList.item(DEFAULT_POSITION);
        String result = nodeElement.getChildNodes().item(DEFAULT_POSITION).getNodeValue();
        return result;
    }

}
