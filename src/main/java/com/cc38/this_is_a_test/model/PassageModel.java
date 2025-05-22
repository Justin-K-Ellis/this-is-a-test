package com.cc38.this_is_a_test.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PassageModel {
    private final String url;

    public PassageModel(@Value("${spring.datasource.url}") String url) {
        this.url = url;
    }

    public Connection getConnection() throws SQLDataException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return conn;
    }

    public ArrayList<HashMap<String, String>> getAll() throws SQLDataException {
        String query = "SELECT * FROM passage;";
        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                HashMap<String, String> passage = new HashMap<>();
                String title = rs.getString("title");
                String content = rs.getString("content");
                passage.put("title", title);
                passage.put("content", content);
                results.add(passage);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return results;
    }

    public Map<String, String> getById(String id) {
        String query = String.format("SELECT * FROM passage WHERE id = %s", id) ;
        System.out.println("=QUERY= " + query);
        Map<String, String> result = new HashMap<>();
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                result.put("title", title);
                result.put("content", content);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

}
