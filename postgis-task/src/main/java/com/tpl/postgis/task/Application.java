/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpl.postgis.task;

import java.sql.SQLException;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author tpl
 */
@SpringBootApplication(scanBasePackages = {"com.*"})

public class Application {
     @Autowired
    ApplicationContext context;

    public static void main(String[] args) throws ParseException, SQLException, ClassNotFoundException, com.vividsolutions.jts.io.ParseException {
       
        SpringApplication.run(Application.class, args);
       
    }
}
