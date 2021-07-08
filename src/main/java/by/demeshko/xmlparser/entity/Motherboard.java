package by.demeshko.xmlparser.entity;

import java.net.Socket;
import java.util.List;

public class Motherboard extends Device{
    private Socket socket;
    private Memory memory;
    private List<Port> outputPorts;
}
