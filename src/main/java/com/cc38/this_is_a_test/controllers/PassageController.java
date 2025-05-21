package com.cc38.this_is_a_test.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassageController {
 
    @GetMapping("/passage")
    public String getAllPassages() {
        return "Here are all the reading passages.";
    }

    @GetMapping("/passage/{id}")
    public String getPassageById(@PathVariable String id) {
        System.out.println(id);
        return "Here is passage " + id;
    }
    
    @PostMapping("/passage")
    public String postPassage(@RequestBody String entity) {
        return "Just created: " + entity;
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
