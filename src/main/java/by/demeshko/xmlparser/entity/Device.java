package by.demeshko.xmlparser.entity;

import java.util.Arrays;
import java.util.List;

public abstract class Device {
    private int id;
    private String deviceName;
    private String origin;
    private int price;
    private boolean critical;
    private boolean peripheral;
    private GroupOfComponents groupOfComponents;
}
