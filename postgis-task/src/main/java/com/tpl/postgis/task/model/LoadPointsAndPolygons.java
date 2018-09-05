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
public class LoadPointsAndPolygons {

    public LoadPointsAndPolygons() {
        
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String points;
    private String polygons;

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getPolygons() {
        return polygons;
    }

    public void setPolygons(String polygons) {
        this.polygons = polygons;
    }
}
