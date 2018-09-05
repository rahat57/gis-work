/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tpl.postgis.task.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author tpl
 */
public class GenerateDBUpdateQueries {
    public static void main(String[] args) {
        Random rand = new Random();
        int max = rand.nextInt(100 - 1 + 1) + 1;
        List<String> queries = new ArrayList<>();
        for (int i = 1; i < 2363; i++) {
           String sql = "update pak_floodaffected_polygon  set m_20110207 = "+(rand.nextInt(100 - 1 + 1) + 1)+",m_20110301 ="+rand.nextInt(100 - 1 + 1) + 1+",m_20110322="+rand.nextInt(100 - 1 + 1) + 1+",\"temp colum\" ="+rand.nextInt(100 - 1 + 1) + 1+" where gid ="+ i;
           queries.add(sql);
        }
        

    }
}
