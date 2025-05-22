package com.cc38.this_is_a_test.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cc38.this_is_a_test.Passage;
import com.cc38.this_is_a_test.model.PassageModel;

@RestController
public class PassageController {
    @Autowired
    private ApplicationContext applicationContext;
 
    @GetMapping("/passage")
    public ArrayList<HashMap<String, String>> getAllPassages() {
        PassageModel pm = applicationContext.getBean(PassageModel.class);

        try {
            ArrayList<HashMap<String, String>> results = pm.getAll();
            return results;
        } catch (SQLException e) {
            System.out.println(e);
        }
        ArrayList<HashMap<String, String>> nullResult = new ArrayList<>();
        return nullResult;
    }

    @GetMapping("/passage/{id}")
    public Map<String, String> getPassageById(@PathVariable String id) {
        System.out.println(id);
        PassageModel pm = applicationContext.getBean(PassageModel.class);
        
        try {
            Map<String, String> result = pm.getById(id);
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        Map<String, String> nullResult = new HashMap<>();
        return nullResult;
    }
    
    @PostMapping("/passage")
    public Passage postPassage(@RequestBody Passage passage) {
        PassageModel pm = applicationContext.getBean(PassageModel.class);
        Passage newPassage = pm.createPassage(passage);
        return newPassage;
    }
    
    @PutMapping("/passage/{id}")
    public String putPassage(@PathVariable String id, @RequestBody String entity) {
        return "Updated passage " + id;
    }

    @DeleteMapping("/passage/{id}")
    public String deletePassage(@PathVariable String id) {
        return "Deleted passage " + id;
    }
}
