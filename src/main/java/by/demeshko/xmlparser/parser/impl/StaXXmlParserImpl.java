package by.demeshko.xmlparser.parser.impl;

import by.demeshko.xmlparser.entity.Cpu;
import by.demeshko.xmlparser.entity.Motherboard;
import by.demeshko.xmlparser.entity.Videocard;
import by.demeshko.xmlparser.entity.types.*;
import by.demeshko.xmlparser.exception.DeviceException;
import by.demeshko.xmlparser.exception.XMLParseException;
import by.demeshko.xmlparser.parser.DeviceParser;
import by.demeshko.xmlparser.parser.XMLAttributes;
import by.demeshko.xmlparser.parser.XMLTag;
import by.demeshko.xmlparser.repository.DeviceRepository;
import by.demeshko.xmlparser.repository.impl.DeviceRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;


public class StaXXmlParserImpl implements DeviceParser {
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";
    private static final String UNKNOWN_XML_TAG = "Unknown XML tag ";
    private final Logger logger = LogManager.getLogger();
    private final DeviceRepository deviceRepository = DeviceRepositoryImpl.getInstance();
    private Cpu.Builder cpuBuilder;
    private Videocard.Builder videocardBuilder;
    private Motherboard.Builder motherboardBuilder;
    private XMLTag deviceType;
    private XMLTag currentDeviceTag;

    @Override
    public void parseDevices(String xmlFilePath) throws DeviceException {
        logger.info("Using file: " + (xmlFilePath.equals("") ?
                xmlFilePath = DOMDeviceParserImpl.PATH_TO_XML :
                xmlFilePath));
        String xmlFile = ClassLoader.getSystemClassLoader().getResource(xmlFilePath).getFile();
        XMLInputFactory factory = XMLInputFactory.newFactory();
        try {
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(xmlFile));
            logger.info("StaX parser start to parse");
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    currentDeviceTag = XMLTag.valueOf(changeTag(startElement.getName().getLocalPart()));
                    switch (currentDeviceTag) {
                        case CPU -> {
                            deviceType = currentDeviceTag;
                            this.cpuBuilder = Cpu.newBuilder();
                            this.cpuBuilder.setId(startElement.getAttributeByName(new QName(XMLAttributes.ID.getValue())).getValue());
                            this.cpuBuilder.setDate(LocalDate.parse(startElement.getAttributeByName(new QName(XMLAttributes.DATE.getValue())).getValue()));
                        }
                        case VIDEOCARD -> {
                            deviceType = currentDeviceTag;
                            this.videocardBuilder = Videocard.newBuilder();
                            this.videocardBuilder.setId(startElement.getAttributeByName(new QName(XMLAttributes.ID.getValue())).getValue());
                            this.videocardBuilder.setDate(LocalDate.parse(startElement.getAttributeByName(new QName(XMLAttributes.DATE.getValue())).getValue()));
                        }
                        case MOTHERBOARD -> {
                            deviceType = currentDeviceTag;
                            this.motherboardBuilder = Motherboard.newBuilder();
                            this.motherboardBuilder.setId(startElement.getAttributeByName(new QName(XMLAttributes.ID.getValue())).getValue());
                            this.motherboardBuilder.setDate(LocalDate.parse(startElement.getAttributeByName(new QName(XMLAttributes.DATE.getValue())).getValue()));
                        }
                        case DEVICES -> deviceType = currentDeviceTag;
                        default -> {
                            event = eventReader.nextEvent();
                            EventHandler(event);
                        }
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    XMLTag endDevice = XMLTag.valueOf(changeTag(endElement.getName().getLocalPart()));
                    switch (endDevice) {
                        case CPU -> deviceRepository.add(cpuBuilder.build());
                        case VIDEOCARD -> deviceRepository.add(videocardBuilder.build());
                        case MOTHERBOARD -> deviceRepository.add(motherboardBuilder.build());
                    }
                }
            }
            logger.info("StaX parser finish parsing");
        } catch (XMLStreamException | FileNotFoundException e) {
            logger.error("Can't parse file ", e);
        }
    }

    private void EventHandler(XMLEvent event) {
        try {
            switch (this.deviceType) {
                case CPU -> parseCpu(event.asCharacters().getData());
                case VIDEOCARD -> parseVideocard((event.asCharacters().getData()));
                case MOTHERBOARD -> parseMotherboard((event.asCharacters().getData()));
                default -> logger.error("Unexpected xml tag: " + this.deviceType);
            }
        } catch (XMLParseException e) {
            logger.error(e);
        }
    }

    private void parseCpu(String data) throws XMLParseException {
        try {
            switch (currentDeviceTag) {
                case DEVICE_NAME -> this.cpuBuilder.setDeviceName(data);
                case ORIGIN -> this.cpuBuilder.setOrigin(data);
                case PRICE -> this.cpuBuilder.setPrice(Integer.parseInt(data));
                case CRITICAL -> this.cpuBuilder.setCritical(Boolean.parseBoolean(data));
                case PERIPHERAL -> this.cpuBuilder.setPeripheral(Boolean.parseBoolean(data));
                case COMPONENT_GROUP -> this.cpuBuilder.setGroupOfComponents(GroupOfComponents.valueOf(data));
                case POWER_CONSUMPTION -> this.cpuBuilder.setEnergyConsumption(Integer.parseInt(data));
                case CPU_SOCKET -> this.cpuBuilder.setCpuSocket(CpuSocket.valueOf(data));
                case ARCHITECTURE -> this.cpuBuilder.setCpuArchitecture(CpuArchitecture.valueOf(data));
                default -> throw new XMLParseException(new StringBuilder()
                        .append(UNKNOWN_XML_TAG)
                        .append(this.currentDeviceTag)
                        .append(" in Cpu").toString());
            }
        } catch (DeviceException e) {
            logger.error(e);
        }
    }

    private void parseVideocard(String data) throws XMLParseException {
        try {
            switch (this.currentDeviceTag) {
                case DEVICE_NAME -> this.videocardBuilder.setDeviceName(data);
                case ORIGIN -> this.videocardBuilder.setOrigin(data);
                case PRICE -> this.videocardBuilder.setPrice(Integer.parseInt(data));
                case CRITICAL -> this.videocardBuilder.setCritical(Boolean.parseBoolean(data));
                case PERIPHERAL -> this.videocardBuilder.setPeripheral(Boolean.parseBoolean(data));
                case COMPONENT_GROUP -> this.videocardBuilder.setGroupOfComponents(GroupOfComponents.valueOf(data));
                case POWER_CONSUMPTION -> this.videocardBuilder.setEnergyConsumption(Integer.parseInt(data));
                case COOLER -> this.videocardBuilder.setCooler(Boolean.parseBoolean(data));
                case HASHRATE -> this.videocardBuilder.setHashRate(Integer.parseInt(data));
                case OUTPUT_PORT -> this.videocardBuilder.setOutputPort(Port.valueOf(data));
                default -> throw new XMLParseException(
                        new StringBuilder()
                                .append(UNKNOWN_XML_TAG)
                                .append(this.currentDeviceTag)
                                .append(" in Videocard").toString());
            }
        } catch (DeviceException e) {
            logger.error(e);
        }
    }

    private void parseMotherboard(String data) throws XMLParseException {
        try {
            switch (this.currentDeviceTag) {
                case DEVICE_NAME -> this.motherboardBuilder.setDeviceName(data);
                case ORIGIN -> this.motherboardBuilder.setOrigin(data);
                case PRICE -> this.motherboardBuilder.setPrice(Integer.parseInt(data));
                case CRITICAL -> this.motherboardBuilder.setCritical(Boolean.parseBoolean(data));
                case PERIPHERAL -> this.motherboardBuilder.setPeripheral(Boolean.parseBoolean(data));
                case COMPONENT_GROUP -> this.motherboardBuilder.setGroupOfComponents(GroupOfComponents.valueOf(data));
                case CPU_SOCKET -> this.motherboardBuilder.setSocket(CpuSocket.valueOf(data));
                case MEMORY_TYPE -> this.motherboardBuilder.setMemory(MemoryType.valueOf(data));
                case SATA -> this.motherboardBuilder.setSata(Integer.parseInt(data));
                default -> throw new XMLParseException(
                        new StringBuilder()
                                .append(UNKNOWN_XML_TAG)
                                .append(this.currentDeviceTag)
                                .append(" in motherboard").toString());
            }
        } catch (DeviceException e) {
            logger.error(e);
        }
    }

    private String changeTag(String name) {
        return name.toUpperCase().replaceAll(HYPHEN, UNDERSCORE);
    }
}
