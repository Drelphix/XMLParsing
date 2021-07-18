package by.demeshko.xmlparser.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class DeviceValidator { 
    private static final Logger LOGGER = LogManager.getLogger();

    public static boolean isFileExists(String filePath)  {
        if(filePath != null && !filePath.isEmpty()){
            File file = new File(filePath);
            return file.isFile() && file.canRead() && file.length() > 0;
        }
        return false;
    }

    public static boolean isValidXMLvsXSD(String xmlPath, String xsdPath){
        SchemaFactory schemaFactory = SchemaFactory.newDefaultInstance();
        final Schema schema;
        try {
            schema = schemaFactory.newSchema(new File(xsdPath));
            final Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            return true;
        } catch (SAXException e) {
            LOGGER.error("XSD Scheme is not valid",e);
        } catch (IOException e) {
            LOGGER.error("XML File is not valid",e);
        }
        return false;
    }

}
