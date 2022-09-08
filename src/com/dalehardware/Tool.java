package com.dalehardware;

public class Tool {
    public Tool(String code, String type, String brand) {
        this.code = code;
        this.type = type;
        this.brand = brand;
    }

    private String code;
    private String type;
    private String brand;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
