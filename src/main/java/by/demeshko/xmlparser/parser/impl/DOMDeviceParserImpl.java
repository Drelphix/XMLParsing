package by.demeshko.xmlparser.parser.impl;

import by.demeshko.xmlparser.entity.Cpu;
import by.demeshko.xmlparser.entity.Device;
import by.demeshko.xmlparser.entity.Motherboard;
import by.demeshko.xmlparser.entity.Videocard;
import by.demeshko.xmlparser.entity.types.*;
import by.demeshko.xmlparser.exception.DeviceException;
import by.demeshko.xmlparser.parser.DeviceParser;
import by.demeshko.xmlparser.parser.XMLAttributes;
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
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.demeshko.xmlparser.parser.XMLTag.*;

public class DOMDeviceParserImpl implements DeviceParser {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int DEFAULT_POSITION = 0;
    private static final String PATH_TO_XML = "devices.xml";
    private static final String INCORRECT_PARAMETER_ERROR = "Input parameter is not correct";
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";
    private List<Device> devices = new ArrayList<>();

    @Override
    public List<Device> parseDevices(String xmlFilePath) throws DeviceException {
        LOGGER.info("Using file: " + (xmlFilePath.equals("") ?
                xmlFilePath = PATH_TO_XML :
                xmlFilePath));
        String xmlFile = ClassLoader.getSystemClassLoader().getResource(xmlFilePath).getFile();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            LOGGER.info("DOM parser start to parse.");
            NodeList cpuList = document.getElementsByTagName(CPU.getValue());
            parseCpu(cpuList);
            NodeList motherboards = document.getElementsByTagName(MOTHERBOARD.getValue());
            parseMotherboard(motherboards);
            NodeList videoCards = document.getElementsByTagName(VIDEOCARD.getValue());
            parseVideocard(videoCards);
            LOGGER.info("Parsing finished.");
            return this.devices;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new DeviceException("DOM parser error: ", e);
        }
    }


    private void parseCpu(NodeList cpuList) {
        for (int i = 0; i < cpuList.getLength(); i++) {
            Element cpuElement = (Element) cpuList.item(i);
            try {
                Cpu cpu = Cpu.newBuilder()
                        .setId(cpuElement.getAttribute(XMLAttributes.ID.name().toLowerCase()))
                        .setDate(LocalDate.parse(cpuElement.getAttribute(XMLAttributes.DATE.getValue()).toLowerCase()))
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
                devices.add(cpu);
            } catch (DeviceException e) {
                LOGGER.error(INCORRECT_PARAMETER_ERROR, e);
            }
        }
    }

    private void parseVideocard(NodeList videocards) {
        for (int i = 0; i < videocards.getLength(); i++) {
            Element videocardElement = (Element) videocards.item(i);
            try {
                Videocard videocard = Videocard.newBuilder()
                        .setId(videocardElement.getAttribute(XMLAttributes.ID.name().toLowerCase()))
                        .setDate(LocalDate.parse(videocardElement.getAttribute(XMLAttributes.DATE.getValue()).toLowerCase()))
                        .setDeviceName(getContent(videocardElement, DEVICE_NAME.name()))
                        .setCritical(Boolean.parseBoolean(getContent(videocardElement, CRITICAL.name())))
                        .setEnergyConsumption(Integer.parseInt(getContent(videocardElement, POWER_CONSUMPTION.name())))
                        .setGroupOfComponents(GroupOfComponents.valueOf(getContent(videocardElement, COMPONENT_GROUP.name())))
                        .setOrigin(getContent(videocardElement, ORIGIN.name()))
                        .setPeripheral(Boolean.parseBoolean(getContent(videocardElement, PERIPHERAL.name())))
                        .setPrice(Integer.parseInt(getContent(videocardElement, PRICE.name())))
                        .setOutputPort(Port.valueOf(getContent(videocardElement, OUTPUT_PORT.name())))
                        .setCooler(Boolean.parseBoolean(getContent(videocardElement, COOLER.name())))
                        .setHashRate(Integer.parseInt(getContent(videocardElement, HASHRATE.name())))
                        .build();
                devices.add(videocard);
            } catch (DeviceException e) {
                LOGGER.error(INCORRECT_PARAMETER_ERROR, e);
            }
        }
    }

    private void parseMotherboard(NodeList motherboards) {
        for (int i = 0; i < motherboards.getLength(); i++) {
            Element motherboardElement = (Element) motherboards.item(i);
            try {
                Motherboard motherboard = Motherboard.newBuilder()
                        .setId(motherboardElement.getAttribute(XMLAttributes.ID.name().toLowerCase()))
                        .setDate(LocalDate.parse(motherboardElement.getAttribute(XMLAttributes.DATE.getValue()).toLowerCase()))
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
                devices.add(motherboard);
            } catch (DeviceException e) {
                LOGGER.error(INCORRECT_PARAMETER_ERROR, e);
            }
        }
    }

    private String getContent(Element element, String type) {
        type = type.replace(UNDERSCORE, HYPHEN).toLowerCase();
        NodeList nodeList = element.getElementsByTagName(type);
        Node nodeElement = nodeList.item(DEFAULT_POSITION);
        return nodeElement.getChildNodes().item(DEFAULT_POSITION).getNodeValue();
    }

}
