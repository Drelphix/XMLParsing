package by.demeshko.xmlparser.exception;

public class XMLParseException extends Exception {
    public XMLParseException() {
    }

    public XMLParseException(String message) {
        super(message);
    }

    public XMLParseException(Throwable throwable) {
        super(throwable);
    }

    public XMLParseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
