package com.dalehardware;

public class Tool {
    public Tool(String code, Inventory.ToolType type, Inventory.Brand brand) {
        this.code = code;
        this.type = type;
        this.brand = brand;
    }

    // Unique identifier for a tool instance.
    private String code;
    // The type of tool. The type also specifies the daily rental charge,
    // and the days for which the daily rental charge applies.
    private Inventory.ToolType type;
    // The brand of the ladder, chain saw, or jackhammer.
    private Inventory.Brand brand;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Inventory.ToolType getType() {
        return type;
    }

    public void setType(Inventory.ToolType type) {
        this.type = type;
    }

    public Inventory.Brand getBrand() {
        return brand;
    }

    public void setBrand(Inventory.Brand brand) {
        this.brand = brand;
    }

}
