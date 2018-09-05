/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpl.postgis.task.controllerDao;

import com.google.gson.Gson;
import com.tpl.postgis.task.model.PakPolygons;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.geotools.data.DataUtilities;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 *
 * @author tpl
 */
public class PakPolygonsRowMapper implements RowMapper {

    GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
    WKTReader reader = new WKTReader(geometryFactory);
   
    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {

        PakPolygons pakpolygons = new PakPolygons();
        ArrayList<SimpleFeature> featurelist = new ArrayList<SimpleFeature>();
        try {
                        SimpleFeatureType TYPE = DataUtilities.createType("location", "id:String,geom:Geometry");
            GeometryJSON gjson = new GeometryJSON();

//            Reader reader = new StringReader(rs.getString("geopolygons"));
//            Polygon p = gjson.readPolygon(reader);
            MultiPolygon p = (MultiPolygon) reader.read(rs.getString("geopolygons").toString());
            SimpleFeatureBuilder fBuild = new SimpleFeatureBuilder(TYPE);
            fBuild.add(rs.getInt("gid"));
            fBuild.add(p);

            SimpleFeature feature = fBuild.buildFeature(null);
//            featurelist.add(feature);
//            FeatureJSON fJson = new FeatureJSON();
//            pakpolygons.setPolygons(fJson.toString(feature));
               pakpolygons.setFeature(feature);

//            pakpolygons.setId_0(rs.getInt("id_0"));
//            pakpolygons.setId_1(rs.getInt("id_1"));
//            pakpolygons.setId_2(rs.getInt("id_2"));
//            pakpolygons.setIso(rs.getString("iso"));
//            pakpolygons.setName_0(rs.getString("name_0"));
//            pakpolygons.setName_1(rs.getString("name_1"));
//            pakpolygons.setName_2(rs.getString("name_2"));
//            pakpolygons.setType_2(rs.getString("type_2"));
            
        } catch (SchemaException ex) {
            Logger.getLogger(PakPolygonsRowMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PakPolygonsRowMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pakpolygons;
    }
}
