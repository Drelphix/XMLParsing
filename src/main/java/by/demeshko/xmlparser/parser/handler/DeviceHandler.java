package by.demeshko.xmlparser.parser.handler;

import by.demeshko.xmlparser.entity.Cpu;
import by.demeshko.xmlparser.entity.Device;
import by.demeshko.xmlparser.entity.Motherboard;
import by.demeshko.xmlparser.entity.Videocard;
import by.demeshko.xmlparser.entity.types.*;
import by.demeshko.xmlparser.exception.DeviceException;
import by.demeshko.xmlparser.exception.XMLParseException;
import by.demeshko.xmlparser.parser.XMLAttributes;
import by.demeshko.xmlparser.parser.XMLTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeviceHandler extends DefaultHandler {
    private static final Logger logger = LogManager.getLogger();
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";
    private Cpu.Builder cpuBuilder;
    private Videocard.Builder videocardBuilder;
    private Motherboard.Builder motherboardBuilder;
    private XMLTag currentDeviceTag;
    private XMLTag deviceType;
    private List<Device> devices = new ArrayList<>();

    @Override
    public void startDocument() throws SAXException {
        logger.info("SaXParser start to parse..");
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info("SaXParser end to parse..");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        XMLTag tag = XMLTag.valueOf(replaceXmlTag(qName));
        switch (tag) {
            case CPU -> {
                this.cpuBuilder = Cpu.newBuilder();
                this.cpuBuilder.setId(attributes.getValue(XMLAttributes.ID.getValue()));
                this.cpuBuilder.setDate(LocalDate.parse(attributes.getValue(XMLAttributes.DATE.getValue())));
                this.deviceType = XMLTag.CPU;
            }
            case MOTHERBOARD -> {
                this.motherboardBuilder = Motherboard.newBuilder();
                this.motherboardBuilder.setId(attributes.getValue(XMLAttributes.ID.getValue()));
                this.motherboardBuilder.setDate(LocalDate.parse(attributes.getValue(XMLAttributes.DATE.getValue())));
                this.deviceType = XMLTag.MOTHERBOARD;
            }
            case VIDEOCARD -> {
                this.videocardBuilder = Videocard.newBuilder();
                this.videocardBuilder.setDate(LocalDate.parse(attributes.getValue(XMLAttributes.DATE.getValue())));
                this.videocardBuilder.setId(attributes.getValue(XMLAttributes.ID.getValue()));
                this.deviceType = XMLTag.VIDEOCARD;
            }
            default -> this.currentDeviceTag = tag;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        XMLTag tag = XMLTag.valueOf(this.replaceXmlTag(qName));
        switch (tag) {
            case CPU -> this.devices.add(this.cpuBuilder.build());
            case MOTHERBOARD -> this.devices.add(this.motherboardBuilder.build());
            case VIDEOCARD -> this.devices.add(this.videocardBuilder.build());
            default -> this.currentDeviceTag = tag;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String deviceParam = new String(ch, start, length);
        deviceParam = deviceParam.replace("\n", "").trim();
        try {
            if (!deviceParam.isEmpty() && this.deviceType != null) {
                switch (this.deviceType) {
                    case CPU -> setCpu(deviceParam);
                    case MOTHERBOARD -> setMotherboard(deviceParam);
                    case VIDEOCARD -> setVideocard(deviceParam);
                }
            }
        } catch (XMLParseException e) {
            logger.error(e);
        }
    }

    public List<Device> getDevices(){
        return this.devices;
    }

    private void setCpu(String data) throws XMLParseException {
        try {
            switch (this.currentDeviceTag) {
                case DEVICE_NAME -> this.cpuBuilder.setDeviceName(data);
                case ORIGIN -> this.cpuBuilder.setOrigin(data);
                case PRICE -> this.cpuBuilder.setPrice(Integer.parseInt(data));
                case CRITICAL -> this.cpuBuilder.setCritical(Boolean.parseBoolean(data));
                case PERIPHERAL -> this.cpuBuilder.setPeripheral(Boolean.parseBoolean(data));
                case COMPONENT_GROUP -> this.cpuBuilder.setGroupOfComponents(GroupOfComponents.valueOf(data));
                case POWER_CONSUMPTION -> this.cpuBuilder.setEnergyConsumption(Integer.parseInt(data));
                case CPU_SOCKET -> this.cpuBuilder.setCpuSocket(CpuSocket.valueOf(data));
                case ARCHITECTURE -> this.cpuBuilder.setCpuArchitecture(CpuArchitecture.valueOf(data));
                default -> throw new XMLParseException("Unknown xml tag " + this.currentDeviceTag + " in CPU");
            }
        } catch (DeviceException e) {
            logger.error(e);
        }
    }

    private void setVideocard(String data) throws XMLParseException {
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
                                .append("Unknown xml tag ")
                                .append(this.currentDeviceTag)
                                .append(" in Videocard").toString());
            }
        } catch (DeviceException e) {
            logger.error(e);
        }
    }

    private void setMotherboard(String data) throws XMLParseException {
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
                                .append("Unknown xml tag ")
                                .append(this.currentDeviceTag)
                                .append(" in Videocard").toString());
            }
        } catch (DeviceException e) {
            logger.error(e);
        }
    }

    private String replaceXmlTag(String tag) {
        return tag.toUpperCase().replace(HYPHEN, UNDERSCORE);
    }
}
