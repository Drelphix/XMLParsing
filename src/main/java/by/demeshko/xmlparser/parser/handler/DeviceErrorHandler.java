package by.demeshko.xmlparser.parser.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DeviceErrorHandler implements ErrorHandler {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        logger.warn(findErrorPosition(exception), exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        logger.error(findErrorPosition(exception), exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        logger.fatal(findErrorPosition(exception), exception.getMessage());
    }

    private String findErrorPosition(SAXParseException e) {
        return "line: " + e.getLineNumber() + ", column: " + e.getColumnNumber();
    }
}
