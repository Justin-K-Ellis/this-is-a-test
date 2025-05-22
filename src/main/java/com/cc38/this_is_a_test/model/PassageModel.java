package com.cc38.this_is_a_test.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cc38.this_is_a_test.Passage;

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

    public ArrayList<Passage> getAll() throws SQLDataException {
        String query = "SELECT * FROM passage;";
        ArrayList<Passage> results = new ArrayList<>();
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Passage passage = new Passage("", "");
                String title = rs.getString("title");
                String content = rs.getString("content");
                passage.updateTitle(title);
                passage.updateContent(content);
                results.add(passage);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return results;
    }

    public Passage getById(String id) {
        String query = String.format("SELECT * FROM passage WHERE id = %s", id) ;
        Passage result = new Passage("", "");
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                result.updateTitle(title);
                result.updateContent(content);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public Passage createPassage(Passage passage) {
        String query = String.format("INSERT INTO passage (title, content) VALUES ('%s', '%s')", passage.getTitle(), passage.getContent());
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
            return passage;
        } catch (SQLException e) {
            System.out.println(e);
        }
        Passage nullPassage = new Passage( "",  "");
        return nullPassage;
    }

    public void updatePassage(Passage passage, String id) {
        String query = String.format("UPDATE passage SET title = '%s', content = '%s' WHERE id = %s", passage.getTitle(), passage.getContent(), id);
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query); 
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deletePassage(String id) {
        String query = String.format("DELETE FROM passage WHERE id = %s", id);
        try {
            Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
