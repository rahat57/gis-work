/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpl.postgis.task.model;

import org.opengis.feature.simple.SimpleFeature;

/**
 *
 * @author tpl
 */
//id_0,iso,name_0,id_1,name_1,id_2,name_2,type_2,engtype_2
public class PakPolygons {

    private String polygons;
    private SimpleFeature feature ;
//    private int gid;
//    private int id_0;
//    private int id_1;
//    private String iso;
//    private int id_2;
//    private String name_0;
//    private String name_1;

    public SimpleFeature getFeature() {
        return feature;
    }

    public void setFeature(SimpleFeature feature) {
        this.feature = feature;
    }

//    public String getName_1() {
//        return name_1;
//    }
//
//    public void setName_1(String name_1) {
//        this.name_1 = name_1;
//    }
//    private int tehsil_c;
//    private String name_2;
//
//    private String type_2;
//
//    public int getGid() {
//        return gid;
//    }
//
//    public void setGid(int gid) {
//        this.gid = gid;
//    }
//
//    public int getId_0() {
//        return id_0;
//    }
//
//    public void setId_0(int id_0) {
//        this.id_0 = id_0;
//    }
//
//    public int getId_1() {
//        return id_1;
//    }
//
//    public void setId_1(int id_1) {
//        this.id_1 = id_1;
//    }
//
//    public String getIso() {
//        return iso;
//    }
//
//    public void setIso(String iso) {
//        this.iso = iso;
//    }
//
//    public int getId_2() {
//        return id_2;
//    }
//
//    public void setId_2(int id_2) {
//        this.id_2 = id_2;
//    }
//
//    public String getName_0() {
//        return name_0;
//    }
//
//    public void setName_0(String name_0) {
//        this.name_0 = name_0;
//    }
//
//    public int getTehsil_c() {
//        return tehsil_c;
//    }
//
//    public void setTehsil_c(int tehsil_c) {
//        this.tehsil_c = tehsil_c;
//    }
//
//    public String getName_2() {
//        return name_2;
//    }
//
//    public void setName_2(String name_2) {
//        this.name_2 = name_2;
//    }
//
//    public String getType_2() {
//        return type_2;
//    }
//
//    public void setType_2(String type_2) {
//        this.type_2 = type_2;
//    }

    public String getPolygons() {
        return polygons;
    }

    public void setPolygons(String polygons) {
        this.polygons = polygons;
    }

}
