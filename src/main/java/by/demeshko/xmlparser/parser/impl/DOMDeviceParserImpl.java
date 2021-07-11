package by.demeshko.xmlparser.parser.impl;

import by.demeshko.xmlparser.entity.*;
import by.demeshko.xmlparser.entity.types.*;
import by.demeshko.xmlparser.exception.DeviceException;
import by.demeshko.xmlparser.parser.DeviceParser;
import by.demeshko.xmlparser.parser.XMLAttributes;
import by.demeshko.xmlparser.repository.DeviceRepository;
import by.demeshko.xmlparser.repository.impl.DeviceRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static by.demeshko.xmlparser.parser.XmlTag.*;

public class DOMDeviceParserImpl implements DeviceParser {
    private static Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POSITION = 0;
    private static final String INCORRECT_PARAMETER_ERROR = "Input parameter is not correct";


    @Override
    public void parseDevices(String xmlFilePath) throws DeviceException {
        logger.info(xmlFilePath.equals("") ?
                xmlFilePath = DOMDeviceParserImpl.PATH_TO_XML :
                "Using path: "+xmlFilePath);
        File xmlFile = new File(xmlFilePath);
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            Element root = document.getDocumentElement();
            logger.info("DOM parser start to parse.");
            NodeList cpuList = document.getElementsByTagName(cpu.name());
            System.out.println(cpuList.item(0).getChildNodes().item(1));
            parseCpu(cpuList);
            NodeList motherboards = document.getElementsByTagName(motherboard.name());
            parseMotherboard(motherboards);
            NodeList videoCards = document.getElementsByTagName(videoCard.name());
            parseVideoCard(videoCards);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new DeviceException("Error parsing with DOM parser", e);
        }
    }


    private void parseCpu(NodeList cpuList){
        DeviceRepository deviceRepository = DeviceRepositoryImpl.getInstance();
        for (int i = 0; i < cpuList.getLength(); i++) {
            Element cpuElement = (Element) cpuList.item(i);
            try {
                Cpu cpu = Cpu.newBuilder().setId(cpuElement.getAttribute(XMLAttributes.id.name()))
                        .setDeviceName(getContent(cpuElement, deviceName.name()))
                        .setCpuArchitecture(CpuArchitecture.valueOf(getContent(cpuElement, architecture.name())))
                        .setCpuSocket(CpuSocket.valueOf(getContent(cpuElement, cpuSocket.name())))
                        .setCritical(Boolean.parseBoolean(getContent(cpuElement, critical.name())))
                        .setEnergyConsumption(Integer.parseInt(getContent(cpuElement, powerConsumption.name())))
                        .setGroupOfComponents(GroupOfComponents.valueOf(getContent(cpuElement, groupOfComponents.name())))
                        .setOrigin(getContent(cpuElement, origin.name()))
                        .setPeripheral(Boolean.parseBoolean(getContent(cpuElement, peripheral.name())))
                        .setPrice(Integer.parseInt(getContent(cpuElement, price.name())))
                        .build();
                deviceRepository.add(cpu);
            } catch (DeviceException e) {
                logger.error(INCORRECT_PARAMETER_ERROR, e);
            }
        }
    }

    private void parseVideoCard(NodeList videoCards){
        DeviceRepository deviceRepository = DeviceRepositoryImpl.getInstance();
        for (int i = 0; i < videoCards.getLength(); i++) {
            Element videoCardElement = (Element) videoCards.item(i);
            try{
                VideoCard videoCard = VideoCard.newBuilder()
                        .setId(videoCardElement.getAttribute(XMLAttributes.id.name()))
                        .setDeviceName(getContent(videoCardElement, deviceName.name()))
                        .setCritical(Boolean.parseBoolean(getContent(videoCardElement, critical.name())))
                        .setEnergyConsumption(Integer.parseInt(getContent(videoCardElement, powerConsumption.name())))
                        .setGroupOfComponents(GroupOfComponents.valueOf(getContent(videoCardElement, groupOfComponents.name())))
                        .setOrigin(getContent(videoCardElement, origin.name()))
                        .setPeripheral(Boolean.parseBoolean(getContent(videoCardElement, peripheral.name())))
                        .setPrice(Integer.parseInt(getContent(videoCardElement, price.name())))
                        .setOutputPort(Port.valueOf(getContent(videoCardElement, outputPort.name())))
                        .setCooler(Boolean.parseBoolean(getContent(videoCardElement, cooler.name())))
                        .setHashRate(Integer.parseInt(getContent(videoCardElement, hashRate.name())))
                        .build();
                deviceRepository.add(videoCard);
            } catch (DeviceException e){
                logger.error(INCORRECT_PARAMETER_ERROR, e);
            }
        }
    }

    private void parseMotherboard(NodeList motherboards){
        DeviceRepository deviceRepository = DeviceRepositoryImpl.getInstance();
        for (int i = 0; i < motherboards.getLength(); i++) {
            Element motherboardElement = (Element) motherboards.item(i);
            try{
                Motherboard motherboard = Motherboard.newBuilder()
                        .setId(motherboardElement.getAttribute(XMLAttributes.id.name()))
                        .setDeviceName(getContent(motherboardElement, deviceName.name()))
                        .setCritical(Boolean.parseBoolean(getContent(motherboardElement, critical.name())))
                        .setGroupOfComponents(GroupOfComponents.valueOf(getContent(motherboardElement, groupOfComponents.name())))
                        .setOrigin(getContent(motherboardElement, origin.name()))
                        .setPeripheral(Boolean.parseBoolean(getContent(motherboardElement, peripheral.name())))
                        .setPrice(Integer.parseInt(getContent(motherboardElement, price.name())))
                        .setSocket(CpuSocket.valueOf(getContent(motherboardElement, cpuSocket.name())))
                        .setSata(Integer.parseInt(getContent(motherboardElement, sata.name())))
                        .setMemory(MemoryType.valueOf(getContent(motherboardElement, memoryType.name())))
                        .build();
                deviceRepository.add(motherboard);
            } catch (DeviceException e){
                logger.error(INCORRECT_PARAMETER_ERROR, e);
            }
        }
    }

    private String getContent(Element element, String type){
        NodeList nodeList = element.getElementsByTagName(type);
        Node nodeElement = nodeList.item(DEFAULT_POSITION);
        String result = nodeElement.getChildNodes().item(DEFAULT_POSITION).getNodeValue();
        return result.replaceAll("\\s+","");
    }

}
