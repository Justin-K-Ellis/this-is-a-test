package com.cc38.this_is_a_test.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ArrayList<Passage>> getAllPassages() {
        PassageModel pm = applicationContext.getBean(PassageModel.class);

        try {
            ArrayList<Passage> results = pm.getAll();
            return ResponseEntity.ok().body(results);
        } catch (SQLException e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/passage/{id}")
    public ResponseEntity<Passage> getPassageById(@PathVariable String id) {
        PassageModel pm = applicationContext.getBean(PassageModel.class);
        
        try {
            Passage result = pm.getById(id);
            System.out.println(result);
            if (result.getTitle().equals("")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PostMapping("/passage")
    public ResponseEntity<Passage> postPassage(@RequestBody Passage passage) {
        PassageModel pm = applicationContext.getBean(PassageModel.class);

        try {
            pm.createPassage(passage);
            return ResponseEntity.status(201).body(passage);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @PutMapping("/passage/{id}")
    public ResponseEntity<Passage> putPassage(@PathVariable String id, @RequestBody Passage passage) {
        PassageModel pm = applicationContext.getBean(PassageModel.class);
        pm.updatePassage(passage, id);

        try {
            Passage targetPassage = pm.getById(id);
            if (targetPassage.getTitle().equals("")) {
                return ResponseEntity.status(404).build();
            }
            return ResponseEntity.ok().body(passage);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/passage/{id}")
    public ResponseEntity<String> deletePassage(@PathVariable String id) {
        PassageModel pm = applicationContext.getBean(PassageModel.class);
        
        try {
            Passage checkPassage = pm.getById(id);
            if (checkPassage.getTitle().equals("")) {
                return ResponseEntity.notFound().build();
            }
            pm.deletePassage(id);
            return ResponseEntity.ok().body(id);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
