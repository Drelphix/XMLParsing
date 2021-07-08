package by.demeshko.xmlparser.exception;

public class DeviceException extends Exception{

    public DeviceException() {
    }

    public DeviceException(String message) {
        super(message);
    }

    public DeviceException(Throwable throwable) {
        super(throwable);
    }

    public DeviceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
