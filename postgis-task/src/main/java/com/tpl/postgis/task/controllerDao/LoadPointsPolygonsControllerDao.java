/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpl.postgis.task.controllerDao;

import com.tpl.postgis.task.model.LoadPointsAndPolygons;
import com.tpl.postgis.task.model.DrwanPolygon;
import com.tpl.postgis.task.model.PakPolygons;
import java.util.List;

/**
 *
 * @author tpl
 */
public interface LoadPointsPolygonsControllerDao {
    List<LoadPointsAndPolygons> getAllPoints();
    List<LoadPointsAndPolygons> getAllPolygons();
    int addShapeData(DrwanPolygon polygon);
    List<PakPolygons> loadAllPakPolygons();
}
