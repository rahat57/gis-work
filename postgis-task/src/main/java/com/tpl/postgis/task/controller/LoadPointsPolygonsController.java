/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpl.postgis.task.controller;

import com.google.gson.Gson;
import com.tpl.postgis.task.model.LoadPointsAndPolygons;
import com.tpl.postgis.task.model.DrwanPolygon;
import com.tpl.postgis.task.model.PakPolygons;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import com.tpl.postgis.task.service.LoadPointsPolygonsService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import org.geotools.data.DataUtilities;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.SchemaException;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author tpl
 */
@RestController
@RequestMapping("/load")
@RequestScope
@CrossOrigin(origins = "http://localhost", allowedHeaders = {})
public class LoadPointsPolygonsController {

    @Autowired
    private LoadPointsPolygonsService ls;

    @RequestMapping(value = "/loadServicePoint", method = RequestMethod.GET)
    public List<LoadPointsAndPolygons> loadServicePoint() {

        return this.ls.loadServicePoint();
    }

    @RequestMapping(value = "/loadServicePolygon", method = RequestMethod.GET)
    public List<LoadPointsAndPolygons> loadServicePolygon() {
        return this.ls.loadServicePolygon();
    }

    @RequestMapping(value = "/addPolygon", method = RequestMethod.POST)
    public int addShapeData(@RequestBody DrwanPolygon polygon) {
        System.err.println("polygon " + polygon.getPolygon());
        return this.ls.addShapeData(polygon);

//        return 0;
    }

    @RequestMapping(value = "/loadUsaPolygons", method = RequestMethod.GET)
    public void loadAllPakPolygons() throws IOException, SchemaException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/home/muhammadali/Desktop/TPLMAPS WORK" + "/" + "usa_polygons.json")));
        List<SimpleFeature> geometryFeaturelist = new ArrayList<>();
        for (PakPolygons obj : this.ls.loadAllPakPolygons()) {
            geometryFeaturelist.add(obj.getFeature());

        }
        try {
            SimpleFeatureType TYPE = DataUtilities.createType("geometry", "geom:Geometry");
        SimpleFeatureCollection collection = new ListFeatureCollection(TYPE, geometryFeaturelist);
        StringWriter geomAsFeatures = new StringWriter();

        FeatureJSON featureJson = new FeatureJSON();
        featureJson.writeFeatureCollection(collection, geomAsFeatures);
//        String jsonSettings = new Gson().toJson(geomAsFeatures);
        writer.append(geomAsFeatures.toString());
//        writer.newLine();
        writer.close();
        } catch (IOException e) {
            System.err.println("error "+e.getMessage());
        }
        
//        return this.ls.loadAllPakPolygons();

    }

    @RequestMapping(value = "/greetings", method = RequestMethod.GET)
    public String greetings() {

        return "Welcome";

    }

}
