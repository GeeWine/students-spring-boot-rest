package hu.geewine.studentsspringbootrest.controller;

import hu.geewine.studentsspringbootrest.model.StudentV1;
import hu.geewine.studentsspringbootrest.service.StudentV1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/students")
public class StudentV1Controller {

    @Autowired
    private StudentV1Service studentV1Service;

    @GetMapping(value = "")
    public List<StudentV1> retrieveAllStudentsV1() {
        return studentV1Service.findAllV1();
    }

    @GetMapping(value = "/filter")
    public List<StudentV1> searchStudentsV1(@RequestParam(value = "search") String search) {
        return studentV1Service.searchStudentsV1(search);
    }

    @GetMapping(value = "/{id}")
    public StudentV1 findStudentV1ById(@PathVariable long id) {
        return studentV1Service.findStudentV1ById(id);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createStudent(@RequestBody StudentV1 studentV1) {
        StudentV1 savedStudent = studentV1Service.saveV1(studentV1);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateStudentV1(@RequestBody StudentV1 studentV1, @PathVariable long id) {
        studentV1Service.updateStudentV1(studentV1, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStudentById(@PathVariable long id) {
        studentV1Service.deleteStudentV1ById(id);
    }

}
