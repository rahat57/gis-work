/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpl.postgis.task.model;

/**
 *
 * @author tpl
 */
public class DrwanPolygon {
    
    public DrwanPolygon(){
    }
    private String polygon;
    private String address;
    private double area;
    private String polygonText;

    public String getPolygonText() {
        return polygonText;
    }

    public void setPolygonText(String polygonText) {
        this.polygonText = polygonText;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
    
    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
