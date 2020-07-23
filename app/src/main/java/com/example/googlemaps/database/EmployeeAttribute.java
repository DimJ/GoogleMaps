package com.example.googlemaps.database;

public class EmployeeAttribute {

    private int employeeId;
    private int attributeId;

    public EmployeeAttribute(int employeeId, int attributeId){
        this.employeeId = employeeId;
        this.attributeId = attributeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }
}
