package br.com.gbonassina.courses.modules.course.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gbonassina.courses.modules.course.CourseEntity;
import br.com.gbonassina.courses.modules.course.services.CourseService;
import jakarta.validation.Valid;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/courses")
public class CourseController {
    
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Object> add(@Valid @RequestBody CourseEntity courseEntity) {
        try {
            var result = courseService.add(courseEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@NonNull @PathVariable UUID id, @RequestBody Map<String, String> requestParams) {
        try {
            var result = courseService.update(id, requestParams);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping(value = "/{id}/active")
    public ResponseEntity<Object> updateState(@NonNull @PathVariable UUID id) {
        try {
            var result = courseService.toogleState(id);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam(required = false) Map<String, String> requestParams) {
        try {
            var result = courseService.get(requestParams);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
