package by.demeshko.xmlparser.parser;

import by.demeshko.xmlparser.entity.Cpu;
import by.demeshko.xmlparser.entity.Device;
import by.demeshko.xmlparser.entity.Motherboard;
import by.demeshko.xmlparser.entity.Videocard;
import by.demeshko.xmlparser.entity.types.*;
import by.demeshko.xmlparser.exception.DeviceException;
import by.demeshko.xmlparser.parser.impl.DOMDeviceParserImpl;
import by.demeshko.xmlparser.parser.impl.SaXXmlParserImpl;
import by.demeshko.xmlparser.parser.impl.StaXXmlParserImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeviceParserTest{
    private List<Device> expected = new ArrayList<>();

    @BeforeEach
    public void init() throws DeviceException {
        Cpu.Builder cpuBuilder = Cpu.newBuilder();
        Videocard.Builder videocardBuilder = Videocard.newBuilder();
        Motherboard.Builder motherboardBuilder = Motherboard.newBuilder();
        Cpu cpu = cpuBuilder.setId("a0001")
                .setDate(LocalDate.parse("1980-03-23"))
                .setDeviceName("Intel Pentium G6400")
                .setOrigin("China")
                .setPrice(220)
                .setCritical(true)
                .setPeripheral(false)
                .setGroupOfComponents(GroupOfComponents.COMPONENT_DEVICE)
                .setEnergyConsumption(58)
                .setCpuSocket(CpuSocket.LGA1200)
                .setCpuArchitecture(CpuArchitecture.SANDY_BRIDGE)
                .build();
        expected.add(cpu);
        Motherboard motherboard = motherboardBuilder.setId("a0002")
                .setDate(LocalDate.parse("1990-03-23"))
                .setDeviceName("ASUS H110")
                .setOrigin("Sweden")
                .setPrice(611)
                .setCritical(true)
                .setPeripheral(false)
                .setGroupOfComponents(GroupOfComponents.COMPONENT_DEVICE)
                .setSocket(CpuSocket.LGA1200)
                .setMemory(MemoryType.DDR3)
                .setSata(6)
                .build();
        expected.add(motherboard);
        Videocard videocard = videocardBuilder.setId("a0004")
                .setDate(LocalDate.parse("2000-05-23"))
                .setDeviceName("Geforce 210")
                .setOrigin("Belarus")
                .setPrice(7028)
                .setCritical(false)
                .setPeripheral(false)
                .setGroupOfComponents(GroupOfComponents.COMPONENT_DEVICE)
                .setEnergyConsumption(15)
                .setCooler(false)
                .setOutputPort(Port.DVI)
                .setHashRate(180)
                .build();
        expected.add(videocard);
    }

    @Test
    public  void DOMDeviceParserEqualsList() throws DeviceException {
        DeviceParser deviceParser = new DOMDeviceParserImpl();
        List<Device> actual =  deviceParser.parseDevices("testDevice.xml");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void SaXXmlParserEqualsList() throws DeviceException {
        DeviceParser deviceParser = new SaXXmlParserImpl();
        List<Device> actual =  deviceParser.parseDevices("testDevice.xml");
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void StAXXmlParserEqualsList() throws DeviceException {
        DeviceParser deviceParser = new StaXXmlParserImpl();
        List<Device> actual =  deviceParser.parseDevices("testDevice.xml");
        Assertions.assertEquals(expected, actual);
    }
}
