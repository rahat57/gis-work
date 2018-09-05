/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpl.postgis.task.service;

import com.tpl.postgis.task.controllerDao.LoadPointsPolygonsRowMapper;
import com.tpl.postgis.task.model.LoadPointsAndPolygons;
import com.tpl.postgis.task.model.DrwanPolygon;
import com.tpl.postgis.task.model.PakPolygons;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author tpl
 */
@Service
public interface LoadPointsPolygonsService {
    public List<LoadPointsAndPolygons> loadServicePoint();
    public List<LoadPointsAndPolygons> loadServicePolygon();
    public int addShapeData(DrwanPolygon polygon);
    public List<PakPolygons> loadAllPakPolygons();
}
