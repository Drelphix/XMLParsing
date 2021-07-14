package by.demeshko.xmlparser.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;

public class DeviceValidatorTest {
    @Test
    public void isValidExistsXMLEqualsTrue()  {
        String xmlFile = ClassLoader.getSystemClassLoader().getResource("testDevice.xml").getFile();
        boolean actual = DeviceValidator.isFileExists(xmlFile);
        Assertions.assertTrue(actual);
    }

    @Test
    public void isValidExistsXMLEqualsFalse() {
        boolean actual = DeviceValidator.isFileExists("device.xml");
        Assertions.assertFalse(actual);
    }

    @Test
    public void isValidExistsXSDEqualsFalse() {
        String xsdFile = ClassLoader.getSystemClassLoader().getResource("devices.xsd").getFile();
        boolean actual = DeviceValidator.isFileExists(xsdFile);
        Assertions.assertTrue(actual);
    }

    @Test
    public void  isValidXMLvsXSDEqualsTrue() {
        String xmlFile = ClassLoader.getSystemClassLoader().getResource("testDevice.xml").getFile();
        String xsdFile = ClassLoader.getSystemClassLoader().getResource("devices.xsd").getFile();
        boolean actual = DeviceValidator.isValidXMLvsXSD(xmlFile, xsdFile);
        Assertions.assertTrue(actual);
    }
}
