/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpl.postgis.task.controllerDao;

import com.tpl.postgis.task.model.LoadPointsAndPolygons;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.geotools.data.DataUtilities;
import org.geotools.referencing.CRS;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author tpl
 */
public class LoadPointsPolygonsRowMapper implements RowMapper {

    public static GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

    public LoadPointsPolygonsRowMapper() {

    }

    @Override
    public Object mapRow(ResultSet rs, int i) throws SQLException {

        LoadPointsAndPolygons pointsAndPolygons = new LoadPointsAndPolygons();

        try {

            SimpleFeatureType TYPE = DataUtilities.createType("location", "id:String,geom:Geometry");
            System.setProperty("org.geotools.referencing.forceXY", "true");
            pointsAndPolygons.setId(rs.getInt("gid"));

            int geopoints = -1;
            int geopolygons = -1;
            try {
                geopoints = rs.findColumn("geopoints");
            } catch (SQLException sql) {
//            System.out.println("exception thrown");
            }
            try {
                geopolygons = rs.findColumn("geopolygons");
            } catch (SQLException sql) {
//    System.out.println("exception thrown");
            }
            GeometryJSON gjson = new GeometryJSON();
            if (geopoints >= 0) {

                Reader reader = new StringReader(rs.getString("geopoints"));
                Point p = gjson.readPoint(reader);
                CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:32638");
                CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:4326");

                MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS);
                Geometry targetGeometry = JTS.transform(p, transform);
                Point reqPoint = (Point) targetGeometry;
                targetGeometry.setSRID(4326);
                SimpleFeatureBuilder fBuild = new SimpleFeatureBuilder(TYPE);
                fBuild.add(rs.getInt("gid"));
                fBuild.add(reqPoint);
//                fBuild.add("blue");
                SimpleFeature feature = fBuild.buildFeature(null);
                FeatureJSON fJson = new FeatureJSON();

                pointsAndPolygons.setPoints(fJson.toString(feature));
//                featurelist.add(feature);
            }
            if (geopolygons >= 0) {
                Reader reader = new StringReader(rs.getString("geopolygons"));
                Polygon p = gjson.readPolygon(reader);
                CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:32638");
                CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:4326");

                MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS);
                Geometry targetGeometry = JTS.transform(p, transform);
                Polygon reqPolygon = (Polygon) targetGeometry;
                SimpleFeatureBuilder fBuild = new SimpleFeatureBuilder(TYPE);
                fBuild.add(rs.getInt("gid"));
                fBuild.add(reqPolygon);
//                fBuild.add("blue");
                SimpleFeature feature = fBuild.buildFeature(null);
                FeatureJSON fJson = new FeatureJSON();
                pointsAndPolygons.setPolygons(fJson.toString(feature));
            }
        } catch (IOException e) {

            System.err.println("error " + e.getMessage());

        } catch (SchemaException ex) {
            Logger.getLogger(LoadPointsPolygonsRowMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MismatchedDimensionException ex) {
            Logger.getLogger(LoadPointsPolygonsRowMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformException ex) {
            Logger.getLogger(LoadPointsPolygonsRowMapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FactoryException ex) {
            Logger.getLogger(LoadPointsPolygonsRowMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pointsAndPolygons;
    }

}
