/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpl.postgis.task.controllerDao;

import com.tpl.postgis.task.model.LoadPointsAndPolygons;
import com.tpl.postgis.task.model.DrwanPolygon;
import com.tpl.postgis.task.model.PakPolygons;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tpl
 */
@Repository
public class LoadPointsPolygonsControllerDaoImpl extends JdbcDaoSupport implements LoadPointsPolygonsControllerDao {

    @Autowired
    DataSource ds;

    @PostConstruct
    public void initialize() {
        setDataSource(ds);
    }

    @Override
    public List<LoadPointsAndPolygons> getAllPoints() {

//        String sql = "SELECT gid, ST_AsGeoJSON(ST_Transform(geom,4326)) As geopoints from servicepoint";
        String sql = "SELECT gid, ST_AsGeoJSON(geom) As geopoints from servicepoint";
        List<LoadPointsAndPolygons> allPointsAsGeoJson = getJdbcTemplate().query(sql, new LoadPointsPolygonsRowMapper());
        return allPointsAsGeoJson;
    }

    @Override
    public List<LoadPointsAndPolygons> getAllPolygons() {

//        String sql = "SELECT gid, ST_AsGeoJSON(ST_Transform(geom,4326)) As geopolygons from servicepolygon";
        String sql = "SELECT gid, ST_AsGeoJSON(geom) As geopolygons from servicepolygon";
        List<LoadPointsAndPolygons> allPolygonAsGeoJson = getJdbcTemplate().query(sql, new LoadPointsPolygonsRowMapper());
        return allPolygonAsGeoJson;
    }

    @Override
    public int addShapeData(DrwanPolygon polygon) {
        String sql = "insert into servicepolygon (ename,geom,area) values (?,ST_Multi(ST_GeomFromText(?,32638)),?)";
        int noInsertedRows = getJdbcTemplate().update(sql, new Object[]{
            polygon.getAddress(),
            polygon.getPolygonText(),
            polygon.getArea(),});
        return noInsertedRows;
    }

    @Override
    public List<PakPolygons> loadAllPakPolygons() {
        long start_time = System.currentTimeMillis();
//        String sql = "SELECT gid,province,province_c,district,district_c,tehsil,tehsil_c,uc_c,uc_name ST_AsGeoJSON(geom) As geopolygons from pak_floodaffected_polygon";
//        String sql1 = "SELECT gid,id_0,iso,name_0,id_1,name_1,id_2,name_2,type_2,ST_AsGeoJSON(geom) As geopolygons from usa_polygon ";
        String sql1 = "SELECT gid,id_0,iso,name_0,id_1,name_1,id_2,name_2,type_2,ST_AsText(geom) As geopolygons from usa_polygon ";
        List<PakPolygons> pakPolygons = getJdbcTemplate().query(sql1, new PakPolygonsRowMapper());
        long end_time = System.currentTimeMillis();
        System.out.println(TimeUnit.MILLISECONDS.toSeconds(end_time-start_time));
        return pakPolygons;

    }

}
