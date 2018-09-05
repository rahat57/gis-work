/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpl.postgis.task.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tpl.postgis.task.controllerDao.LoadPointsPolygonsControllerDao;
import com.tpl.postgis.task.model.LoadPointsAndPolygons;
import com.tpl.postgis.task.model.DrwanPolygon;
import com.tpl.postgis.task.model.PakPolygons;
import com.tpl.postgis.task.service.LoadPointsPolygonsService;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geotools.geojson.geom.GeometryJSON;
import com.vividsolutions.jts.geom.Polygon;
import org.geotools.geometry.jts.JTSFactoryFinder;

/**
 *
 * @author tpl
 */
@Service
public class LoadPointsPolygonsServiceImpl implements LoadPointsPolygonsService {

    @Autowired
    LoadPointsPolygonsControllerDao loadControllerDao;

    @Override
    public List<LoadPointsAndPolygons> loadServicePoint() {
        return loadControllerDao.getAllPoints();
    }

    @Override
    public List<LoadPointsAndPolygons> loadServicePolygon() {
        return loadControllerDao.getAllPolygons();
    }

    @Override
    public int addShapeData(DrwanPolygon polygon) {
        try {

            String featurePolygon = polygon.getPolygon();
            GeometryJSON gjson = new GeometryJSON();
            Reader reader = new StringReader(featurePolygon);
            Polygon p = gjson.readPolygon(reader);
            polygon.setPolygonText(p.toText());
            Double area = p.getArea();
            polygon.setArea(area);
            return loadControllerDao.addShapeData(polygon);

        } catch (IOException ex) {
            Logger.getLogger(LoadPointsPolygonsServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loadControllerDao.addShapeData(polygon);
    }

    @Override
    public List<PakPolygons> loadAllPakPolygons() {
        return loadControllerDao.loadAllPakPolygons();
    }

}
